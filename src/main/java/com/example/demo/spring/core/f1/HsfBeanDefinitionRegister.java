//package com.example.demo.spring.core.f1;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.support.BeanDefinitionRegistry;
//import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
//import org.springframework.core.type.AnnotationMetadata;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class HsfBeanDefinitionRegister implements ImportBeanDefinitionRegistrar {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(HsfBeanDefinitionRegister.class);
//
//    private static final String RESOURCE_PATTERN = "**/*.class";
//    //生成的HSF Bean名称到代理的Service Class的映射
//    private static final Map<String, Class<?>> HSF_UNDERLYING_MAPPING = new HashMap<String, Class<?>>();
//
//    @Override
//    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
//        AnnotationAttributes annAttr = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(HsfComponentScan.class.getName()));
//        String[] basePackages = annAttr.getStringArray("value");
//        if (ObjectUtils.isEmpty(basePackages)) {
//            basePackages = annAttr.getStringArray("basePackages");
//        }
//        if (ObjectUtils.isEmpty(basePackages)) {
//            basePackages = getPackagesFromClasses(annAttr.getClassArray("basePackageClasses"));
//        }
//        if (ObjectUtils.isEmpty(basePackages)) {
//            basePackages = new String[] {ClassUtils.getPackageName(importingClassMetadata.getClassName())};
//        }
//        List<TypeFilter> includeFilters = extractTypeFilters(annAttr.getAnnotationArray("includeFilters"));
//        //增加一个包含的过滤器,扫描到的类只要不是抽象的,接口,枚举,注解,及匿名类那么就算是符合的
//        includeFilters.add(new HsfTypeFilter());
//        List<TypeFilter> excludeFilters = extractTypeFilters(annAttr.getAnnotationArray("excludeFilters"));
//        List<Class<?>> candidates = scanPackages(basePackages, includeFilters, excludeFilters);
//        if (candidates.isEmpty()) {
//            LOGGER.info("扫描指定HSF基础包[{}]时未发现复合条件的基础类", basePackages.toString());
//            return;
//        }
//        //注册HSF后处理器,为HSF对象注入环境配置信息
//        registerHsfBeanPostProcessor(registry);
//        //注册HSF
//        registerBeanDefinitions(candidates, registry);
//    }
//    private List<Class<?>> scanPackages(String[] basePackages, List<TypeFilter> includeFilters, List<TypeFilter> excludeFilters) {
//        List<Class<?>> candidates = new ArrayList<Class<?>>();
//        for (String pkg : basePackages) {
//            try {
//                candidates.addAll(findCandidateClasses(pkg, includeFilters, excludeFilters));
//            } catch (IOException e) {
//                LOGGER.error("扫描指定HSF基础包[{}]时出现异常", pkg);
//                continue;
//            }
//        }
//        return candidates;
//    }
//
//    private List<Class<?>> findCandidateClasses(String basePackage, List<TypeFilter> includeFilters, List<TypeFilter> excludeFilters) throws IOException {
//        if (LOGGER.isDebugEnabled()) {
//            LOGGER.debug("开始扫描指定包{}下的所有类" + basePackage);
//        }
//        List<Class<?>> candidates = new ArrayList<Class<?>>();
//        String packageSearchPath = CLASSPATH_ALL_URL_PREFIX + replaceDotByDelimiter(basePackage) + '/' + RESOURCE_PATTERN;
//        ResourceLoader resourceLoader = new DefaultResourceLoader();
//        MetadataReaderFactory readerFactory = new SimpleMetadataReaderFactory(resourceLoader);
//        Resource[] resources = ResourcePatternUtils.getResourcePatternResolver(resourceLoader).getResources(packageSearchPath);
//        for (Resource resource : resources) {
//            MetadataReader reader = readerFactory.getMetadataReader(resource);
//            if (isCandidateResource(reader, readerFactory, includeFilters, excludeFilters)) {
//                Class<?> candidateClass = transform(reader.getClassMetadata().getClassName());
//                if (candidateClass != null) {
//                    candidates.add(candidateClass);
//                    LOGGER.debug("扫描到符合要求HSF基础类:{}" + candidateClass.getName());
//                }
//            }
//        }
//        return candidates;
//    }
//
//    private void registerBeanDefinitions(List<Class<?>> internalClasses, BeanDefinitionRegistry registry) {
//        for (Class<?> clazz : internalClasses) {
//            if (HSF_UNDERLYING_MAPPING.values().contains(clazz)) {
//                LOGGER.debug("重复扫描{}类,忽略重复注册", clazz.getName());
//                continue;
//            }
//            String beanName = generateHsfBeanName(clazz);
//            RootBeanDefinition rbd = new RootBeanDefinition(HSFSpringProviderBean.class);
//            registry.registerBeanDefinition(beanName, rbd);
//            if (registerSpringBean(clazz)) {
//                LOGGER.debug("注册HSF基础[{}]Bean", clazz.getName());
//                registry.registerBeanDefinition(ClassUtils.getShortNameAsProperty(clazz), new RootBeanDefinition(clazz));
//            }
//            HSF_UNDERLYING_MAPPING.put(beanName, clazz);
//        }
//    }
//
//    private void registerHsfBeanPostProcessor(BeanDefinitionRegistry registry) {
//        String beanName = ClassUtils.getShortNameAsProperty(HsfBeanPostProcessor.class);
//        if (!registry.containsBeanDefinition(beanName)) {
//            registry.registerBeanDefinition(beanName, new RootBeanDefinition(HsfBeanPostProcessor.class));
//        }
//    }
//
//    private String generateHsfBeanName(Class<?> underlying) {
//        String interfaceName = underlying.getInterfaces()[0].getSimpleName();
//        String beanName = HSFSpringProviderBean.class.getSimpleName() + "#" + interfaceName;
//        if (HSF_UNDERLYING_MAPPING.containsKey(beanName)) {
//            beanName = beanName + "#" + getNextOrderSuffix(interfaceName);
//        }
//        return beanName;
//    }
//
//    private Integer getNextOrderSuffix(String className) {
//        int order = 1;
//        for (String hsfBeanName : HSF_UNDERLYING_MAPPING.keySet()) {
//            if (hsfBeanName.substring(hsfBeanName.indexOf("#") + 1).startsWith(className)) {
//                String curOrder = hsfBeanName.substring((HSFSpringProviderBean.class.getSimpleName() + "#" + className).length());
//                if (!StringUtils.isEmpty(curOrder)) {
//                    order = Math.max(Integer.valueOf(curOrder), order);
//                }
//            }
//        }
//        return order;
//    }
//
//    /**
//     * @param filterAttributes
//     * @return
//     */
//    private List<TypeFilter> typeFiltersFor(AnnotationAttributes filterAttributes) {
//        List<TypeFilter> typeFilters = new ArrayList<TypeFilter>();
//        FilterType filterType = filterAttributes.getEnum("type");
//
//        for (Class<?> filterClass : filterAttributes.getClassArray("classes")) {
//            switch (filterType) {
//                case ANNOTATION:
//                    Assert.isAssignable(Annotation.class, filterClass,
//                            "@HsfComponentScan 注解类型的Filter必须指定一个注解");
//                    Class<Annotation> annotationType = (Class<Annotation>)filterClass;
//                    typeFilters.add(new AnnotationTypeFilter(annotationType));
//                    break;
//                case ASSIGNABLE_TYPE:
//                    typeFilters.add(new AssignableTypeFilter(filterClass));
//                    break;
//                case CUSTOM:
//                    Assert.isAssignable(TypeFilter.class, filterClass,
//                            "@HsfComponentScan 自定义Filter必须实现TypeFilter接口");
//                    TypeFilter filter = BeanUtils.instantiateClass(filterClass, TypeFilter.class);
//                    typeFilters.add(filter);
//                    break;
//                default:
//                    throw new IllegalArgumentException("当前TypeFilter不支持: " + filterType);
//            }
//        }
//        return typeFilters;
//    }
//
//    private String[] getPackagesFromClasses(Class[] classes) {
//        if (ObjectUtils.isEmpty(classes)) {
//            return null;
//        }
//        List<String> basePackages = new ArrayList<String>(classes.length);
//        for (Class<?> clazz : classes) {
//            basePackages.add(ClassUtils.getPackageName(clazz));
//        }
//        return (String[])basePackages.toArray();
//    }
//
//    private String replaceDotByDelimiter(String path) {
//        return StringUtils.replace(path, ".", "/");
//    }
//
//    protected boolean isCandidateResource(MetadataReader reader, MetadataReaderFactory readerFactory, List<TypeFilter> includeFilters,
//                                          List<TypeFilter> excludeFilters) throws IOException {
//        for (TypeFilter tf : excludeFilters) {
//            if (tf.match(reader, readerFactory)) {
//                return false;
//            }
//        }
//        for (TypeFilter tf : includeFilters) {
//            if (tf.match(reader, readerFactory)) {
//                return true;
//            }
//        }
//        return false;
//    }
//    private Class<?> transform(String className) {
//        Class<?> clazz = null;
//        try {
//            clazz = ClassUtils.forName(className, this.getClass().getClassLoader());
//        } catch (ClassNotFoundException e) {
//            LOGGER.info("未找到指定HSF基础类{%s}", className);
//        }
//        return clazz;
//    }
//    private List<TypeFilter> extractTypeFilters(AnnotationAttributes[] annAttrs) {
//        List<TypeFilter> typeFilters = new ArrayList<TypeFilter>();
//        for (AnnotationAttributes filter : annAttrs) {
//            typeFilters.addAll(typeFiltersFor(filter));
//        }
//        return typeFilters;
//    }
//
//    /**
//     * @param beanClass
//     * @return
//     */
//    private boolean registerSpringBean(Class<?> beanClass) {
//        return beanClass.getAnnotation(HsfComponent.class).registerBean();
//    }
//
//    public static Class<?> getUnderlyingClass(String hsfBeanName) {
//        return HSF_UNDERLYING_MAPPING.get(hsfBeanName);
//    }
//
//}

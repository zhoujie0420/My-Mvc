package org.jiezhou.springframework.reader;

import org.jiezhou.springframework.entity.BeanDefinition;
import org.jiezhou.springframework.entity.BeanReference;
import org.jiezhou.springframework.entity.PropertyValue;
import org.jiezhou.springframework.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileFilter;
import java.io.InputStream;
import java.util.Set;
import java.util.function.Predicate;

/**
 * @author: elk
 * @create: 2024-01-10 16:57
 **/

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(ResourceLoader resourceLoader) {
        super(resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(String location) throws Exception {
        getResourceLoader().
    }


    protected void doLoadBeanDefinitions(InputStream inputStream) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(inputStream);
        // 解析 bean
        registerBeanDefinition(document);
        inputStream.close();
    }

    public void registerBeanDefinition(Document document) {
        Element root = document.getDocumentElement();
        parseBeanDefinitions(root);
    }

    protected void parseBeanDefinitions(Element root) {
        // 解析 bean
        NodeList nodeList = root.getChildNodes();
        // 确定是否注解配置
        String basePackage = null;

        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i) instanceof Element) {
                Element ele = (Element) nodeList.item(i);
                if (ele.getTagName().equals("component-scan")) {
                    basePackage = ele.getAttribute("base-package");
                    break;
                }
            }
        }
        if (basePackage != null) {
            parseAnnotation(basePackage);
            return;
        }
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node instanceof Element) {
                Element ele = (Element) node;
                if (ele.getTagName().equals("bean")) {
                    parseBeanDefinition(ele);
                } else if (ele.getTagName().equals("aop-config")) {
                    processProxyDefinition(ele);
                }
            }
        }
    }

    protected void parseAnnotation(String basePackage) {
        getClasses(basePackage);
    }

    private void findANdAddClassesInPackageByFile(String packageName, String packagePath, final boolean recursive, Set<Class<?> >classes){
        File file = new File(packagePath);
        if (!file.exists() || !file.isDirectory()) {
            return;
        }

        File[] dirFiles = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return (recursive && file.isDirectory()) || (pathname.getName().endsWith(".class"));
            }
        });
        for (File dirFile : dirFiles) {
            if (dirFile.isDirectory()) {
                findANdAddClassesInPackageByFile(packageName + "." + dirFile.getName(), dirFile.getAbsolutePath(), recursive, classes);
            } else {
                String className = dirFile.getName().substring(0, dirFile.getName().length() - 6);
                try {
                    classes.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + "." + className));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        }
    }

    protected void processBeanDefinition(Element ele) {
        String name = ele.getAttribute("name");
        String className = ele.getAttribute("class");
        boolean singleton = true;
        if (ele.hasAttribute("scope") && ele.getAttribute("scope").equals("prototype")) {
            singleton = false;
        }
        BeanDefinition beanDefinition = new BeanDefinition();
        processProperty(ele, beanDefinition);
        beanDefinition.setBeanClassName(className);
        beanDefinition.setIsSingleton(singleton);
        getRegistry().put(name, beanDefinition);
    }


    private void processProperty(Element ele,BeanDefinition beanDefinition){
        NodeList property = ele.getElementsByTagName("property");
        for (int i = 0; i < property.getLength(); i++) {
            Node node = property.item(i);
            if(node instanceof Element){
                Element propertyEle = (Element) node;
                String name = propertyEle.getAttribute("name");
                String value = propertyEle.getAttribute("value");
                if(value != null && value.length() > 0) {
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, value));
                }else {
                    String ref = propertyEle.getAttribute("ref");
                    if (ref == null || ref.length() == 0) {
                        throw new IllegalArgumentException("Configuration problem: <property> element for property '"
                                + name + "' must specify a ref or value");
                    }
                    BeanReference beanReference = new BeanReference(ref);
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, beanReference));
                }
            }
        }
    }

    protected void processProxyDefinition(Element ele) {

    }
}

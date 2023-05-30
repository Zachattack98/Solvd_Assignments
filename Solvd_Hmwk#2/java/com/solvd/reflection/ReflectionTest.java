package com.solvd.reflection;

import java.lang.reflect.*;
import java.util.Arrays;

import com.solvd.lab2.ServiceShop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReflectionTest {
        private static final Logger REFLECTION_LOGGER = LogManager.getLogger(ReflectionTest.class);

        public static void main(String[] args) {
            try {
                Class<?> testServiceShop = Class.forName("com.solvd.lab2.ServiceShop");
                Class<?>[] params = {String.class, ServiceShop.class};

                REFLECTION_LOGGER.info("Class Name: " + testServiceShop.getName());

                Field[] fields = testServiceShop.getDeclaredFields();
                for (Field field : fields) {
                    REFLECTION_LOGGER.info("Field Name(s): " + field.getName());
                    REFLECTION_LOGGER.info("Field Type(s): " + field.getType());
                    REFLECTION_LOGGER.info("Field Modifier(s): " + Modifier.toString(field.getModifiers()));
                }

                try {
                    Constructor<?> constructor = testServiceShop.getDeclaredConstructor(params);
                    REFLECTION_LOGGER.info("Constructor Name(s): " + constructor.getName());
                    REFLECTION_LOGGER.info("Constructor Parameter(s): " + Arrays.toString(constructor.getParameterTypes()));
                    REFLECTION_LOGGER.info("Constructor Modifier(s): " + Modifier.toString(constructor.getModifiers()));

                    Method[] methods = testServiceShop.getDeclaredMethods();
                    for (Method method: methods) {
                        REFLECTION_LOGGER.info("Method Name(s): " + method.getName());
                        REFLECTION_LOGGER.info("Method Return Type(s): " + method.getReturnType());
                        REFLECTION_LOGGER.info("Method Parameter(s): " + Arrays.toString(method.getParameterTypes()));
                        REFLECTION_LOGGER.info("Method Modifier(s): " + Modifier.toString(method.getModifiers()));
                    }
                }
                catch (NoSuchMethodException nsme) {
                    REFLECTION_LOGGER.error("Constructor not found!!!");
                }
            }
            catch (ClassNotFoundException cnfe) {
                REFLECTION_LOGGER.info(cnfe.getMessage());
            }
        }
}

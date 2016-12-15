/**
 * am: Assert-Mocks for unit-testing Java servlet API code
 * Copyright (c) 2016, Sandeep Gupta
 * 
 * https://sangupta.com/projects/am
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sangupta.am;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sangupta.jerry.util.AssertUtils;

/**
 * Utility classes that help in building the <code>am</code>
 * framework.
 * 
 * @author sangupta
 * @since 1.0.0
 */
public class AmUtils {
	
	public static <T> T getFieldForName(Object instance, String name, Class<T> castTo) {
		if(instance == null) {
			return null;
		}
		
		if(AssertUtils.isEmpty(name)) {
			return null;
		}
		
		Class<?> clazz = instance.getClass();
		List<Field> fields = getAllFields(clazz);
		if(fields.isEmpty()) {
			return null;
		}
		
		for(Field field : fields) {
			if(field.getName().equals(name)) {
				try {
					field.setAccessible(true);
					Object object = field.get(instance);
					if(object == null) {
						return null;
					}
					
					return castTo.cast(object);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}
		}
		
		return null;
	}

	private static List<Field> getAllFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        populateAllFields(clazz, fields);
        return fields;
    }
    
    private static void populateAllFields(Class<?> clazz, List<Field> fields) {
        if(clazz == null) {
            return;
        }
        
        Field[] array = clazz.getDeclaredFields();
        if(array != null && array.length > 0) {
            fields.addAll(Arrays.asList(array));
        }
        
        if(clazz.getSuperclass() == null) {
            return;
        }
        
        populateAllFields(clazz.getSuperclass(), fields);
    }
    
}

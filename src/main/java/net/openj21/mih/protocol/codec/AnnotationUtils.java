/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package net.openj21.mih.protocol.codec;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

import net.openj21.mih.datatype.basic.SEQUENCE_ELEMENT;
import net.openj21.utils.ReflectionUtils;

/**
 * Static methods for working with annotations via reflection.
 */
public class AnnotationUtils {
    /**
     * Creates a new AnnotationUtils instance.
     */
    public AnnotationUtils() {
        // empty
    }

    /**
     * Returns the given annotationClass if it is present on the given class or
     * one of its direct superclasses. Otherwise returns null.
     *
     * @param cls             the class where we are looking for the annotation
     * @param annotationClass the annotation class to search for
     * @return the annotation, or null
     */
    public <A extends Annotation> A getAnnotation(Class<?> cls, Class<A> annotationClass) {
        A annotation = null;

        // recurse up the class hierarchy
        for (Class<?> superCls = cls; annotation == null && superCls != null; superCls = superCls.getSuperclass()) {
            annotation = superCls.getAnnotation(annotationClass);
        }

        return annotation;
    }

    /**
     * Returns a Collection containing all fields of the given SEQUENCE class
     * that are annotated with the SEQUENCE_ELEMENT annotation. The collection
     * is sorted on the order of the sequence elements.
     *
     * @param sequenceClass a class
     * @return a Collection containing all fields of the given SEQUENCE class
     */
    public Collection<Field> getSequenceFields(Class<?> sequenceClass) {
        final SortedMap<Integer, Field> toEncodeFields = new TreeMap<Integer, Field>();
        ReflectionUtils.doWithFields(sequenceClass, new ReflectionUtils.FieldCallback() {
            
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                SEQUENCE_ELEMENT encodedAnnotation = field.getAnnotation(SEQUENCE_ELEMENT.class);
                if (encodedAnnotation != null) {
                    if (toEncodeFields.put(encodedAnnotation.order(), field) != null) {
                        throw new IllegalStateException("Duplicate " + SEQUENCE_ELEMENT.class.getSimpleName() + " order: " + field);
                    }
                }
            }
        });

        return toEncodeFields.values();
    }
}
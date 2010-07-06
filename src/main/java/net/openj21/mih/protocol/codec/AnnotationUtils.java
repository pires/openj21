/*
 *  OpenJ21 Copyright (C) 2010 Paulo Pires
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free 
 * Software Foundation; either version 2.1 of the License, or (at your option) 
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more 
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 * 
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
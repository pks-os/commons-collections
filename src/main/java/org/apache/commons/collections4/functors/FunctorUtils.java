/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.collections4.functors;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.Transformer;

/**
 * Internal utilities for functors.
 *
 * @since 3.0
 */
final class FunctorUtils {

    /**
     * A very simple method that coerces Closure<? super T> to Closure<T>.
     * <p>This method exists
     * simply as centralized documentation and atomic unchecked warning
     * suppression.
     *
     * @param <T> the type of object the returned closure should "accept"
     * @param closure the closure to coerce.
     * @return the coerced closure.
     */
    @SuppressWarnings("unchecked")
    static <T> Closure<T> coerce(final Closure<? super T> closure) {
        return (Closure<T>) closure;
    }

    /**
     * A very simple method that coerces Predicate<? super T> to Predicate<T>.
     * Due to the {@link Predicate#test(T)} method, Predicate<? super T> is
     * able to be coerced to Predicate<T> without casting issues.
     * <p>This method exists
     * simply as centralised documentation and atomic unchecked warning
     * suppression.
     *
     * @param <T> the type of object the returned predicate should "accept"
     * @param predicate the predicate to coerce.
     * @return the coerced predicate.
     */
    @SuppressWarnings("unchecked")
    static <T> Predicate<T> coerce(final Predicate<? super T> predicate) {
        return (Predicate<T>) predicate;
    }

    /**
     * A very simple method that coerces Transformer<? super I, ? extends O> to Transformer<I, O>.
     * <p>This method exists
     * simply as centralised documentation and atomic unchecked warning
     * suppression.
     *
     * @param <I> the type of object the returned transformer should "accept"
     * @param <O> the type of object the returned transformer should "produce"
     * @param transformer the transformer to coerce.
     * @return the coerced transformer.
     */
    @SuppressWarnings("unchecked")
    static <I, O> Transformer<I, O> coerce(final Transformer<? super I, ? extends O> transformer) {
        return (Transformer<I, O>) transformer;
    }

    /**
     * Clone the closures to ensure that the internal reference can't be messed with.
     *
     * @param closures  the closures to copy
     * @return the cloned closures
     */
    @SuppressWarnings("unchecked")
    static <E> Closure<E>[] copy(final Closure<? super E>... closures) {
        if (closures == null) {
            return null;
        }
        return (Closure<E>[]) closures.clone();
    }

    /**
     * Clone the predicates to ensure that the internal reference can't be messed with.
     * Due to the {@link Predicate#test(T)} method, Predicate<? super T> is
     * able to be coerced to Predicate<T> without casting issues.
     *
     * @param predicates  the predicates to copy
     * @return the cloned predicates
     */
    @SuppressWarnings("unchecked")
    static <T> Predicate<T>[] copy(final Predicate<? super T>... predicates) {
        if (predicates == null) {
            return null;
        }
        return (Predicate<T>[]) predicates.clone();
    }

    /**
     * Copy method
     *
     * @param transformers  the transformers to copy
     * @return a clone of the transformers
     */
    @SuppressWarnings("unchecked")
    static <I, O> Transformer<I, O>[] copy(final Transformer<? super I, ? extends O>... transformers) {
        if (transformers == null) {
            return null;
        }
        return (Transformer<I, O>[]) transformers.clone();
    }

    /**
     * Validates the consumers to ensure that all is well.
     *
     * @param consumers  the consumers to validate.
     */
    static void validate(final Consumer<?>... consumers) {
        Objects.requireNonNull(consumers, "closures");
        for (int i = 0; i < consumers.length; i++) {
            if (consumers[i] == null) {
                throw new NullPointerException("closures[" + i + "]");
            }
        }
    }

    /**
     * Validate the predicates to ensure that all is well.
     *
     * @param predicates  the predicates to validate
     * @return predicate array
     */
    static <T> Predicate<? super T>[] validate(final Collection<? extends Predicate<? super T>> predicates) {
        Objects.requireNonNull(predicates, "predicates");
        // convert to array like this to guarantee iterator() ordering
        @SuppressWarnings("unchecked") // OK
        final Predicate<? super T>[] preds = new Predicate[predicates.size()];
        int i = 0;
        for (final Predicate<? super T> predicate : predicates) {
            preds[i] = predicate;
            if (preds[i] == null) {
                throw new NullPointerException("predicates[" + i + "]");
            }
            i++;
        }
        return preds;
    }

    /**
     * Validate the predicates to ensure that all is well.
     *
     * @param predicates  the predicates to validate
     */
    static void validate(final java.util.function.Predicate<?>... predicates) {
        Objects.requireNonNull(predicates, "predicates");
        for (int i = 0; i < predicates.length; i++) {
            if (predicates[i] == null) {
                throw new NullPointerException("predicates[" + i + "]");
            }
        }
    }

    /**
     * Validate method
     *
     * @param functions  the transformers to validate
     */
    static void validate(final Function<?, ?>... functions) {
        Objects.requireNonNull(functions, "functions");
        for (int i = 0; i < functions.length; i++) {
            if (functions[i] == null) {
                throw new NullPointerException("functions[" + i + "]");
            }
        }
    }

    /**
     * Restricted constructor.
     */
    private FunctorUtils() {
    }

}

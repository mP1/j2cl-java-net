/*
 * Copyright 2019 Miroslav Pokorny (github.com/mP1)
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
 *
 */

package walkingkooka.j2cl.java.net;

import walkingkooka.javashader.ShadedClassTesting;
import walkingkooka.predicate.Predicates;
import walkingkooka.reflect.PackageName;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public abstract class JavaNetTestCase<T> implements ShadedClassTesting<T> {

    // ShadedClassTesting...............................................................................................

    @Override
    public final Predicate<Constructor<?>> requiredConstructors() {
        return Predicates.always();
    }

    @Override
    public final Predicate<Field> requiredFields() {
        return (f) -> false == f.getName().equals("serialVersionUID");
    }

    @Override
    public final UnaryOperator<Class<?>> typeMapper() {
        return ShadedClassTesting.typeMapper(PackageName.with("walkingkooka.j2cl.java.net"),
                PackageName.with("java.net"));
    }
}

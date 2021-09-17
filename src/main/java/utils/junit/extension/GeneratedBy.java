package utils.junit.extension;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

import java.util.function.Supplier;

// If no Retention annotation is present on an annotation type declaration, the retention policy defaults to RetentionPolicy.CLASS
// If an @Target meta-annotation is not present on an annotation type T , then an annotation of type T may be written as a modifier for any declaration except a type parameter declaration
@Retention( RetentionPolicy.RUNTIME )
@Target( { ElementType.FIELD } )
public @interface GeneratedBy {
    Class< ? extends Supplier< ? > > value();
}

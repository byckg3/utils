package utils.junit.extension;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class GeneratorExtenion implements BeforeEachCallback
{
    private static final Map< Class< ? extends Supplier<?> >, Supplier<?> > supplierCache = new HashMap<>();

    private static final Class< GeneratedBy > annotationClass = GeneratedBy.class;

    @Override
    public void beforeEach( ExtensionContext context ) throws Exception
    {
        Optional<?> optTestInstance = context.getTestInstance();
        if ( !optTestInstance.isPresent() ) {
            return;
        }
        
        var testInstance = optTestInstance.get();
        var testClass = testInstance.getClass();
        var annotatedFields = getAnnotatedFields( testClass, annotationClass );

        for ( Field eachField : annotatedFields )
        {
            Supplier<?> supplierInstance = null;
            var supplierClass = getSupplierClassFrom( eachField );

            if ( supplierCache.containsKey( supplierClass ) )
            {
                supplierInstance = supplierCache.get( supplierClass );
            }
            else
            {
                supplierInstance = createSupplierInstance( supplierClass );
                supplierCache.put( supplierClass, supplierInstance );
            }
            eachField.setAccessible( true );
            eachField.set( testInstance, supplierInstance.get() );
        }
    }

    private < T extends Annotation > List< Field > getAnnotatedFields( Class<?> targetClass, Class<T> annotationClass )
    {
        var annotatedFields = Stream.of( targetClass.getDeclaredFields() )
                                    .filter( eachField -> eachField.isAnnotationPresent( annotationClass ) )
                                    .collect( Collectors.toList() );
        return annotatedFields;
    }

    private Class< ? extends Supplier<?> > getSupplierClassFrom( Field field )
    {
        var targetAnnotation = field.getAnnotation( annotationClass );

        return targetAnnotation.value();
    }

    private Supplier<?> createSupplierInstance( Class< ? extends Supplier<?> > supplierClass ) throws Exception
    {
        return supplierClass.getDeclaredConstructor().newInstance();
    }
}

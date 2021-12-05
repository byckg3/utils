package utils;

import java.util.List;
import java.util.Map;

public class SystemPrinter
{
    public static < E > void println( int[] array )
    {
        StringBuilder elements = new StringBuilder( "[" );
        for ( int i = 0; i < array.length; i++ )
        {
            elements.append( " " + array[ i ] );
        }
        elements.append( " ]" );

        System.out.println( elements.toString() );
    }

    public static < E > void println( List< E > list )
    {
        StringBuilder elements = new StringBuilder( "[" );
        for ( E eachElement: list )
        {
            elements.append( " " + eachElement.toString() );
        }
        elements.append( " ]" );

        System.out.println( elements.toString() );
    }

    public static < K, V > void println( Map< K, V > map )
    {
        StringBuilder entries = new StringBuilder();
        for ( var eachEntry: map.entrySet() )
        {
            entries.append( eachEntry.getKey() );
            entries.append( ":\t" );
            entries.append( eachEntry.getValue() );
            entries.append( "\n" );
        }
        System.out.println( entries.toString() );
    }
}

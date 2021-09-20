package utils;

import java.security.SecureRandom;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class DataGenerator
{
    public static int MIN_INT = 1;
    public static int MAX_INT = 100;
    public static int DATA_SIZE = 10;

    public static String TIMESTAMP_PATTERN = "yyyyMMddHHmmssSSS";

    public static IntStream generateRandomIntStream()
    {
        return DoubleStream.generate( Math::random )
                           .mapToInt( randomValue -> ( int ) ( randomValue * ( MAX_INT - MIN_INT + 1 ) + MIN_INT ) )
                           .distinct()
                           .limit( DATA_SIZE );
    }
    
    public static List< Integer > generateRandomIntList()
    {
        return generateRandomIntStream().boxed().collect( Collectors.toList() );
    }

    public static String generateTimestamp()
    {
        var formatter = DateTimeFormatter.ofPattern( TIMESTAMP_PATTERN );
      
        return LocalDateTime.now().format( formatter );
    }

    public static String generateBase64RandomString( int length )
    {
        if ( length < 0 ) {
            throw new RuntimeException( "length must be > 0" );
        }

        String randomString = "";
        SecureRandom random = new SecureRandom();
        Base64.Encoder encoder = Base64.getEncoder();

        int arrayLength = 4 * ( ( int ) Math.ceil( length / 4.0 ) ) * 6 / 8;
        byte[] randomBytes = new byte[ arrayLength ];
        random.nextBytes( randomBytes );

        randomString = encoder.encodeToString( randomBytes );

        return randomString.substring( 0, length );
    }
}

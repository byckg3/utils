package utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.IntStream;

class DataGeneratorTests
{
    @ParameterizedTest( name = "@MethodSource#{index} : test_generateBase64RandomString( {arguments} )" )
    @MethodSource( value = "utils.DataGenerator#generateRandomIntStream" )
    void test_generateBase64RandomString( Integer length ) throws Exception
    {
        String randomString = DataGenerator.generateBase64RandomString( length );
        assertEquals( length, randomString.length() );
    }

    @Test
    void test_generateRandomIntStream() throws Exception
    {
        IntStream intStream = DataGenerator.generateRandomIntStream();
        assertEquals( DataGenerator.DATA_SIZE, ( int ) intStream.count() );
    }

    @Test
    void test_generateRandomIntList() throws Exception
    {
        List< Integer > intList = DataGenerator.generateRandomIntList();
        assertEquals( DataGenerator.DATA_SIZE, ( int ) intList.size() );
    }
}

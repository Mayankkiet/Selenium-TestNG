package guru99;


import org.testng.annotations.DataProvider;
public class DataProviderClass1 {
        @DataProvider(name="SearchProvider")
        public static Object[][] getDataFromDataprovider(){
            return new Object[][] {
                { "Guru99", "India" },
                { "Krishna", "UK" },
                { "Bhupesh", "USA" }
            };  
}}

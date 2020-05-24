import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Main {

    public static int[] generateRandomInts(int num, int min, int max) {
        int[] years = new int[num];
        Random random = new Random();


        for(int i = 0; i < num; i++) {
            years[i] = random.nextInt((max - min) + 1) + min;
        }

        return years;
    }

    private static  final String[] manufacturers = {
            "Abarth",
            "Alfa Romeo",
            "Aston Martin",
            "Audi",
            "Bentley",
            "BMW",
            "Bugatti",
            "Cadillac",
            "Chevrolet",
            "Chrysler",
            "Citroën",
            "Dacia",
            "Daewoo",
            "Daihatsu",
            "Dodge",
            "Donkervoort",
            "DS",
            "Ferrari",
            "Fiat",
            "Fisker",
            "Ford",
            "Honda",
            "Hummer",
            "Hyundai",
            "Infiniti",
            "Iveco",
            "Jaguar",
            "Jeep",
            "Kia",
            "KTM",
            "Lada",
            "Lamborghini",
            "Lancia",
            "Land Rover",
            "Landwind",
            "Lexus",
            "Lotus",
            "Maserati",
            "Maybach",
            "Mazda",
            "McLaren",
            "Mercedes-Benz",
            "MG",
            "Mini",
            "Mitsubishi",
            "Morgan",
            "Nissan",
            "Opel",
            "Peugeot",
            "Porsche",
            "Renault",
            "Rolls-Royce",
            "Rover",
            "Saab",
            "Seat",
            "Skoda",
            "Smart",
            "SsangYong",
            "Subaru",
            "Suzuki",
            "Tesla",
            "Toyota",
            "Volkswagen",
            "Volvo"
    };

    public static String[] generateMakes(int num) {
        String[] makes = new String[num];

        Random random = new Random();
        for(int i = 0; i < num; i++) {
            makes[i] = manufacturers[random.nextInt(manufacturers.length)];
        }

        return makes;
    }

    //modified version of:
    //https://stackoverflow.com/questions/4951997/generating-random-words-in-java
    public static String[] generateGibberish(int num, int minWordCount, int maxWordCount)
    {
        String[] randomStrings = new String[num];
        Arrays.fill(randomStrings, "");

        Random random = new Random();
        for(int i = 0; i < num; i++)
        {
            int wordcount =  random.nextInt(maxWordCount - minWordCount) + minWordCount;
            for (int k = 0; k < wordcount; k++) {
                char[] word = new char[random.nextInt(8)+3]; // words of length 3 through 10. (1 and 2 letter words are boring.)
                for(int j = 0; j < word.length; j++)
                {
                    word[j] = (char)('a' + random.nextInt(26));
                }
                randomStrings[i] += new String(word) + " ";
            }

        }
        return randomStrings;
    }

    public static void main(String[] args) {
        int numberOfRows = 500000;


        try {
            FileWriter fw = new FileWriter("nft_input.csv");

            int[] years = generateRandomInts(numberOfRows, 1980, 2020);
            String[] makes = generateMakes(numberOfRows);
            String[] models = generateGibberish(numberOfRows, 1, 5);
            String[] descriptions = generateGibberish(numberOfRows, 0, 20);
            int[] prices = generateRandomInts(numberOfRows, 500, 50000);

            fw.write("Year,Make,Model,Description,Price\n");

            for (int i = 0; i < numberOfRows; i++) {
                fw.write(years[i] + "," +
                        makes[i] + "," +
                        models[i] + "," +
                        "\"" + descriptions[i] + "\"," +
                        prices[i] + "\n");
            }




            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

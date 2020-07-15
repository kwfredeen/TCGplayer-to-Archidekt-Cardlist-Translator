package TranslatorUI;

import java.util.ArrayList;
import java.util.List;

public class TextProcessor {
    /**
     * Transforms a TCGplayer cardlist to an Archidekt cardlist
     * @param input List of cards as a String, separated by newlines, in the following format:
     *              quantity name (collector number if specified) [set code]
     * @param preserveCollectorNumber boolean representing whether or not to include the collector number in the output,
     *                                true to include, false to exclude
     * @return List of cards as a String, separated by newlines, in the following format:
     *         quantity name (set code)collector number if specified and included
     */
    public static String process(String input, boolean preserveCollectorNumber, boolean appendMaybeboard){
        StringBuilder output = new StringBuilder();
        //split the input into lines
        String lines[] = input.split("\n");

        for(String line : lines){
            //extract the relevant information from each line
            //extract quantity. Because the name may have spaces we can only split by spaces for quantity
            String lineParts[] = line.split(" ", 2);
            String quantity = lineParts[0];

            //extract the card name and collector number, if applicable
            String name = "";
            String collectorNumber = "";
            //check if the line has parentheses
            if(lineParts[1].contains("(")) {
                int parenPosition = lineParts[1].lastIndexOf("(");
                name = lineParts[1].substring(0, parenPosition - 1);

                int bracketPosition = lineParts[1].lastIndexOf("[");
                collectorNumber = lineParts[1].substring(parenPosition + 1, bracketPosition - 2);
//                if(collectorNumber.contains(")")){
//                    //the card name contains parentheses so we need to extract the collector number from that
//                    int secondParenPosition = lineParts[1].lastIndexOf("(");
//                    collectorNumber = collectorNumber = lineParts[1].substring(secondParenPosition + 1, bracketPosition - 2);
//                }
            } else {
                int bracketPosition = lineParts[1].lastIndexOf("[");
                name = lineParts[1].substring(0, bracketPosition - 1);
            }

            //extract the set code
            int bracketPosition = lineParts[1].lastIndexOf("[");
            String setCode = lineParts[1].substring(bracketPosition + 1, lineParts[1].length() - 1);

            //build the new line
            //add default information
            output.append(quantity + " " + name + " " + "(" + setCode + ")");
            //add collector number if applicable
            if(preserveCollectorNumber){
                output.append(collectorNumber);
            }
            //add maybeboard if applicable
            if(appendMaybeboard){
                output.append(" `Maybeboard`");
            }
            output.append("\n");
        }
        return output.toString();
    }

    /**
     * counts the occurences of character in inputString
     * @param character character to count
     * @param inputString String to count in
     * @return number of times character occurs in inputString
     */
    static int countInString(char character, String inputString){
        int count = 0;
        for(char c : inputString.toCharArray()) {
            if(c == character){
                count++;
            }
        }
        return count;
    }
}

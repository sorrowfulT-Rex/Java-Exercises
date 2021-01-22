import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _67dd {

  public static void main(String[] args) throws IOException {
	  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	  String input;
	  int lines = 0;
	  int words = 0;
	  int chars = 0;

	  while (!(input = br.readLine()).equals("")) {

		  lines++;

		  boolean inWord = false;

		  for (int i = 0; i < input.length(); i++) {

			  if (Character.isLetterOrDigit(input.charAt(i))) {
				  chars++;
				  if (!inWord) {
					  words++;
				  }
				  inWord = true;
			  } else {
				  inWord = false;
			  }
		  }

	  }

	  System.out.println("Lines: " + lines);
	  System.out.println("Words: " + words);
	  System.out.println("Characters: " + chars);
  }
}

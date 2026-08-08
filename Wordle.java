int[] count(char[] arr) {
    int[] counts = new int[26];
    for(int i = 0; i < arr.length; i += 1) {
        char c = arr[i];
        if(c >= 'a' && c <= 'z') {
            counts[c - 'a'] += 1;
        }
    }
    return counts;
}

boolean checkInput(String str) {
    if(str.length() != 5) {
        return false;
    }

    for(int i = 0; i < str.length(); i += 1) {
        char c = str.charAt(i);
        if(c > 'z' || c < 'a') {
            return false;
        }
    }
    return true;
}


void main() {
    String secret = "enact";
    int count = secret.length();
    int tryTimes = 6;
    IO.println("I want to let you know, this is a five letter word.");
    IO.println("Please type enough five letters, without special symbols, cases are insensitive.");
    boolean flag = false;
    // Make array for the answer:
    char[] arr = new char[count];
    for(int i = 0; i < count; i += 1) {
        arr[i] = secret.charAt(i);
    }
    while(!flag) {
        char[] sol = new char[count];
        String answer = (IO.readln("Tell me your guess: ")).toLowerCase();
        tryTimes -= 1;

        // if player make first guess correct -> stop
        IO.println(String.valueOf(sol));
        if(answer.equals(secret)) {
            IO.println("You won! The answer was " + secret);
            break;
        } else {
            flag = false;
        }

        // if try times ran out -> stop
        if(tryTimes <= 0) {
        IO.println("You lost! The answer was: " + secret);
        break;
        }

        // test if other conditions of inputs are true or not
        if(checkInput(answer) == false) {
            IO.println("Your input is not valid!");
            break;
        }

        // Create the table of counted letters
         int[] countLetters = count(arr);

        // check for letters at the correct position
        // if yes, leaves * and minus the cound, else just let _ for now
        for(int i = 0; i < answer.length(); i += 1) {
            if(answer.charAt(i) == secret.charAt(i)) {
                sol[i] = '*';
                countLetters[answer.charAt(i) - 'a'] -= 1;
            } else {
                sol[i] = '_';
            }
        }

        for(int i = 0; i < answer.length(); i += 1) {
            if(sol[i] == '*') {
                continue;
            }

            char c = answer.charAt(i);
            int index = c - 'a';

            if(index >= 0 && index < 26 && countLetters[index] > 0) {
                sol[i] = '?';
                countLetters[index] -= 1;
            }
        }
    }
}

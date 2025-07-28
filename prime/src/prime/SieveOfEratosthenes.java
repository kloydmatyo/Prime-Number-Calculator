package prime;

public class SieveOfEratosthenes {

    void sieveOfEratosthenes(int n, StringBuilder outputBuilder) {
        // Create a boolean array "prime[0..n]" and initialize all entries as true
        boolean [] prime = new boolean [n+1];
        for (int i = 0; i <= n; i++) {
            prime[i]=true;   
        }
     

        // Mark non-prime numbers using the Sieve of Eratosthenes algorithm
        for (int p = 2; p * p <= n; p++) {
            if(prime[p]){
                for (int i = p * p; i <= n; i += p) {
                    prime[i] = false;
                }
            }
        }

        // Print all prime numbers in a 10x grid format
        int count = 0;
        for (int i = 2; i <= n; i++) {
            if (prime[i]) {
                outputBuilder.append(String.format("%2d,", i)); // Format each number with a width of 4 characters
                count ++;
                 if (count % 10 == 0) {
                    outputBuilder.append("\n");
                } else {
                    outputBuilder.append(""); // Add a space between prime numbers
                }
            }
        }
    }

    String getPrimes(int n) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
/*
CS 410 OS
Lastname Firstname
Lab 04
*/

#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>
#include <time.h>

// Signal handler for SIGFPE (arithmetic error)
void handle_sigfpe(int signum) {
    printf("Caught SIGFPE (Arithmetic Exception)! Division by zero detected.\n");
    exit(1); 
}

// Signal handler for SIGTERM (termination signal)
void handle_sigterm(int signum) {
    printf("Caught SIGTERM! Program terminated gracefully.\n");
    exit(0);
}

int main() {

    // Seed the random number generator (avoids same sequence every time)
    srand(time(NULL));

    // Define two integer arrays of size 5
    int array1[5] = {100, 200, 300, 400, 500};
    int array2[5] = {5, 0, 2, 4, 1}; 

    // Register signal handlers
    signal(SIGFPE, handle_sigfpe); 
    signal(SIGTERM, handle_sigterm); 

    // Perform random divisions with delay
    int iteration = 0;
    while (1) {
        // Randomly select indices for division
        int index1 = rand() % 5;
        int index2 = rand() % 5;

        // Display numbers chosen for division
        printf("Dividing %d by %d...\n", array1[index1], array2[index2]);

        // Perform integer division (triggers SIGFPE if division by zero)
        int result = array1[index1] / array2[index2]; 
        printf("Result: %d\n", result);

        // Delay for one second
        sleep(1);

        // Terminate after 10 iterations using SIGTERM (called from within the program)
        iteration++;
        if (iteration >= 10) {
            // Trigger termination via SIGTERM
            raise(SIGTERM); 
        
        }
    }

    return 0;
}
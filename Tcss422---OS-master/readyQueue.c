
//Arshdeep Singh
//Audrey
//Matthew Seto
//
// October 16, 2015
// Operating Systems (TCSS 422)



#include <stdio.h>
#include <stdlib.h>
#include <float.h>
#include <ctype.h>
#include <stdbool.h>

#include "readyQueue.h"







//This method is used when the user enters a number.
int doMath(int orig, int toAdd) {
    int final = orig * 10;
    final = final + toAdd;
    
    return final;
}


//Convert a char to an int.
int characterTodecimal(char ch) {
    //printf("in characterTodeceimal, ch is %c\n", ch);
    if (ch != '\n') {
        
        int decimalNum;
        decimalNum = (int) ch;
        
        if (decimalNum < 58) {
            decimalNum = decimalNum - '0'; //if char is < 10
        } else {
            decimalNum = decimalNum - 55;
        }
        return decimalNum;
    }
    
    return 0;
}



//Check if the character is a period
int periodCheck(char ch) {
    if (ch == '.') {
        return 1;
    } else {
        return 0;
    }
    
}




pcb createPcb(int userGivenPriority){
	static int id = 0;
	pcb pcb;

	pcb.proc_id=id;
	pcb.priority_class_integer=userGivenPriority;

	//assign later when we get to use this
	//pcb.state;

	id++;
	return pcb;

}





Queue createQueue() {
  Queue Q;
  Q.count = 0;
  //allocate memory for the queue
  //Q = malloc(sizeof(Queue));
  Q.head = NULL;
  Q.tail = NULL;
  return Q;
}

//enqueue new item to the tail
//dequeue items from the head
void enqueue(Queue *myQ, pcb myPcb){


	Node *newNode = (Node*) malloc(sizeof(Node));
	newNode->pcb = myPcb;
	newNode->next = NULL;


	//if the queue is null
	if(myQ->head == NULL){
		myQ->head = newNode;
	} else {
		//if not null, add it to the tail
		myQ->tail->next = newNode;
	}

	myQ->tail = newNode;
	//increment the count of nodes
	myQ->count++;

	//return 0;
}

pcb dequeue(Queue *myQ){

	//TODO: **** Check for memory leaks later*****


	/////////////////////////////////////////
	//           Image Description         //
	//   Q----->|pcb1|------->|pcb2|NULL|  //
	// Queue    Head node     Tail Node    //
	/////////////////////////////////////////
	// this "newHeadNode" is just going to help
	// us to point to the next node in the queue
	// so that we can dequeue the head node, and
	// the next node will become the new head,
	// and then we will free the memory for this
	// temporary "newHeadNode" node
	Node *newHeadNode = myQ->head;
	pcb pcb = newHeadNode->pcb;
	myQ->head = newHeadNode->next;
	myQ->count--;
	free(newHeadNode);
	
	return pcb;



//
//	//takes the pcb from the head, returns it
//
//	//make a holder for the head pcb
//	pcb myPcb = myQ->head;
//	//move the head to head.next
//	myQ->head = myQ->head->next;
//	//decrement count
//	myQ->count--;
//	//return the pcb
//	return *myPcb;

}

pcb peek(Queue *myQ){

	//get the pcb from the head
	pcb myPcb = myQ->head->pcb;
	//return it
	return myPcb;

}

pcb* createPCB(int newID, int newPri, State newState){
	pcb *myPcb;
	//allocate memory for it
	myPcb=(pcb *)malloc(sizeof(pcb));
	myPcb->proc_id = newID;
	myPcb->priority_class_integer = newPri;
	myPcb->state = newState;
	return myPcb;
}



//hello



int main (int argc, char* argv[]){
	// line that is needed to be run on eclipse
    setvbuf(stdout, NULL, _IONBF, 0);

    int ino;
    char userval;
    int i;
    int PRIORITIES = 64;
    bool invalidEntered = false;
    
    FILE *f = fopen("fileOutput.txt", "w");
    if (f == NULL){
    	printf("Error opening fileOutput.txt");
    	exit(1);
    }
    
    
	//make a new array of queues with 64 places
	Queue priorityArray [PRIORITIES];
	for(i = 0; i < PRIORITIES; i++){
        priorityArray[i] = createQueue();
        //printf("Created Queue for index %d\n", i);
    }

    printf("\n"); //make a new line before printing program question so that user isnt confused.
    
    
	//Accepts input for new processes until user enters an invalid character.
	do{
	    printf("Enter a priority value for a new process, (0-63)\nor type a some invalid number to finish entering processes: \n");
        
        userval = getchar();
        
        
        if (!isdigit(userval)){
            //break out
            invalidEntered = true;
            
        } else if (isdigit(userval)) { //handle input where input is number
            
            int ino = characterTodecimal(userval);
            int periodFound = false;
            char next = getchar();

            while (next != '\n') {
                if (periodCheck(next)) {
                    periodFound = true;
                    invalidEntered = true;
                    
                } else {
                    ino = doMath(ino, characterTodecimal(next));
                    //printf("ino is %d\n", ino);
                    
                }
                
                next = getchar();
            }
            
            if (!periodFound) {
                
                if(ino >= 0 && ino < PRIORITIES) {
                    //do work to put it in the queue
                    //make the new pcb
                    pcb newPcb = createPcb(ino);
                    //put it in the appropriate queue
                    enqueue(&priorityArray[ino],newPcb);
                    //print the priority number,
                    printf("Priority number: %d\t ID number: %d\n",
                           priorityArray[ino].tail->pcb.priority_class_integer, priorityArray[ino].tail->pcb.proc_id);
                    
                    printf("\n");
                } else {
                    invalidEntered = true;
                }
            }
        }

	} while (!invalidEntered);
	
    
    
    
    
    printf("\n"); //make a new line before printing program question so that user isnt confused.
    
	//clears the ready queue and terminates the program
	printf("Emptying the Ready Queue!\n");
	//run through the priorities, empty their respective queues
	for (i = 0; i < PRIORITIES; i++){
		while(priorityArray[i].count > 0){
			pcb curPcb = dequeue(&priorityArray[i]);
			//print to the console
			printf("Running Process: %d,\t Priority: %d\n", curPcb.proc_id, curPcb.priority_class_integer);
			//print to the output file
			fprintf(f, "Running Process: %d,\t Priority: %d\n", curPcb.proc_id, curPcb.priority_class_integer);
		}
	}	
	return 0;
}





//how about doing scanf like this:
//scanf("%c%f%d", &cha, &flo, &inte);
//it will first look for a char then float then int.
//do if checks to see if char is null, if not then print error.
//else do if check to see if float is null, if not print error
//finally check if int is null, if not then continue with program.







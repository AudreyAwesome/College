#include <stdio.h>
#include <stdlib.h>
#include "readyQueue.h"

int main (int argc, char* argv[]){
	
  int userVal; 
  int i;
	//make a new array of queues with 64 places
	Queue priorityArray [64];
	for(i = 0; i < 64; i++){
	  priorityArray[i] = *createQueue();
	  printf("Created Queue for index %d\n", i);
	}

	//prompt the user for an input
	  printf("Please enter a priority value for a new process (0-63):\n");
	  scanf("%d", &userVal);
	  if(userVal >= 0 && userVal<=63){
	    //do work to put it in the queue
	    printf("Successfully added to Ready Queue\n");
	  } else {
	    printf("Invalid priority specified\n");
	  }
}

Queue* createQueue() {
  Queue *Q;
  //allocate memory for the queue
  Q=(Queue *)malloc(sizeof(Queue));
  Q->head = NULL;
  Q->tail = NULL;
  return Q;
}

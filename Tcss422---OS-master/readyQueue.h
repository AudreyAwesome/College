#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>




typedef enum {new, running, waiting, interrupted, halted} State;

//pcb struct pg. 243
typedef struct pcb{
	int proc_id; 					//ID number of the PCB
	int priority_class_integer;				//priority of the PCB (0-63)
    State state; 				//enum state
    //struct pcb * next;          //next pcb in the queue/linked list
} pcb;

//typedef pcb * pcb_p;




/*
 * Created a Node that will be the basic structure
 * 	in our queue, and will point to a pcb struct
 */
typedef struct Node {
	pcb pcb;
	struct Node *next;
} Node;






typedef struct Queue {

	Node *head;			//head of the queue
	Node *tail;			//tail of the queue
    int count;          //count of elements


	//methods declarations
	void (*enqueue)(struct Queue*, struct pcb);
	pcb (*dequeue)(struct Queue*);
	pcb (*peek)(struct Queue*);
	void (*printQueue)(struct Queue*);



} Queue;


//declare method to create new PCB
//pcb createPCB(int, int, State);


// method prototypes
Queue createQueue();
void enqueue(Queue *myQ, pcb myPcb);
pcb dequeue(Queue *myQ);
pcb peek(Queue *myQ);
void printQueue(Queue *myQ);





typedef enum {new, running, waiting, interrupted, halted} State;

//pcb struct pg. 243
typedef struct pcb{
	int id; 					//ID number of the PCB
	int priority;				//priority of the PCB (0-63)
    State state; 				//enum state
    struct pcb * next;          //next pcb in the queue/linked list
} pcb;

typedef pcb * pcb_p; 


typedef struct Queue {
    int count;              	//count of elements
	struct node *head;			//head of the queue
	struct node *tail;			//tail of the queue
} Queue;

//declare method to create new PCB
pcb* createPCB(int, int, State);

// declare methods for the queue
Queue* createQueue();
Queue* peek();
Queue* enqueue();
Queue* dequeue();


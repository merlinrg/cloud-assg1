import socket
import os
import select
import sys

def prompt():
   sys.stdout.write('<You> ')
   sys.stdout.flush()

HOST = '127.0.0.1'
PORT = 6746
try:
  client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
except socket.error:
    print 'Failed to create socket'
    sys.exit()

client_socket.connect((HOST, PORT))

print 'Connected to remote host. Start sending messages'
prompt()

while 1:

    socket_list = [sys.stdin, client_socket]

    read_sockets, write_sockets, error_sockets = select.select(socket_list, [], [])

    for sock in read_sockets:
        if sock == client_socket:
            data = sock.recv(4096)
            if not data:
                print '\nDisconnected from chat server'
                sys.exit()
            else:
                sys.stdout.write(data)
                prompt()
        else:
            msg = sys.stdin.readline()
            client_socket.send('\r<Client>: ' + msg)
            prompt()
	    p,q1,q2=map(int,raw_input("enter p,q1,q2").split())
	    r1,r2=map(int,raw_input("enter r1,r2").split()) 
	    b1,b2=map(int,raw_input("enter b1,b2").split())
	    c1=(p*q1)+(2*r1+b1)
	    c2=(p*q2)+(2*r2+b2)
	    print"c1=",c1
	    print"c2=",c2
	    client_socket.send(str(c1)+":"+str(c2))
	    sm=(c1+c2)%p
	    mult=(c1*c2)%p
            


	  #  client_socket.send(str(sm))


	    print"decrypt=",sm
	    if sm%2==0:
	   	         b=0
	    else:
	  	         b=1

	    
            print"b=",b,"for addition"
	    if b==0:
	                 print "noise is even"
	    else:
	                 print "noise is odd"
 	        
	    if mult%2==0:
	                 b=0
	    else:
			 b=1
	    print"b=",b,"for multiplication"
	    
	    if b==0:
                         print "noise is even"
            else:
                         print "noise is odd"




	   # client_socket.send(str(b))





	

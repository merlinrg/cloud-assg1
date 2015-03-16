
import select  
import socket
import sys
from thread import *

def prompt():
    sys.stdout.write('<You> ')
    sys.stdout.flush()

 
HOST = '127.0.0.1'   # Symbolic name meaning all available interfaces
PORT = 6746  # Arbitrary non-privileged port
RECV_BUFFER = 4098

 
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
print 'Socket created'
 
#Bind socket to local host and port
try:
    s.bind((HOST, PORT))
except socket.error as msg:
    print 'Bind failed. Error Code : ' + str(msg[0]) + ' Message ' + msg[1]
    sys.exit()
     
print 'Socket bind complete'
 
#Start listening on socket
s.listen(10)
print 'Socket now listening'
 
input = [s, sys.stdin]

print 'Chat Program'
prompt()

while 1:

    inputready, outputready, exceptready = select.select(input,[],[])

    for sock in inputready:

        if sock == s:
            client, address = s.accept()
            input.append(client)
            #data = sock.recv(RECV_BUFFER)
            #if data:
                #sys.stdout.write(data)
	elif sock == sys.stdin:
            data = sock.readline()
            for ss in input:
               if ss not in(s, sys.stdin):
                  ss.send(data)
        else:
            data = sock.recv(RECV_BUFFER)
            if data:
                #sys.stdout.write(data)
	        c1=data.split(":")[0]
		c2=data.split(":")[1]
	        #b=int(data.split(":")[2])
           	print "C1 = ",c1
		print "C2 = ",c2
           	#print"b=",b
           	#if sm%2==0:
                #        b=0
           	#else:
                #       b=1
           	#print"b=",b

		
		
            else:
                msg = sys.stdin.readline()
                s.send('\r<Server>: ' + msg)
                prompt()



s.close()

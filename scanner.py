import socket, threading
from pip._vendor.distlib.compat import raw_input
host = raw_input("Enter an Address:- ")
ip = socket.gethostbyname(host)
threads= []
open_ports = {}
def try_port(ip, port, delay, open_ports):
 sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
 sock.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
 sock.settimeout(delay)
 result=sock.connect_ex((ip, port))
 if result == 0:
 open_ports[port] = 'open'
 return True
 else:
 open_ports[port] = 'closed'
 return None
def scan_ports(ip, delay):
 lst = [80, 23, 53, 57, 22, 443, 8080, 3389, 143, 456, 25, 110]
 for port in range(0, 1023):
 thread = threading.Thread(target=try_port, args=(ip, port, delay, open_ports))
 threads.append(thread)
 for i in range(0, 1023):
 threads[i].start() 
 for i in range(0, 1023):
 threads[i].join()
 for i in lst:
 if open_ports[i] == 'open':
 print('\nport number ' +str(i) + ' is malicious')
 print('\n scan complete!!')
if __name__== "__main__":
 scan_ports(ip, 3) 

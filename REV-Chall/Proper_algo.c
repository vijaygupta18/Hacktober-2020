#include<stdio.h>
#include<stdlib.h>
#include<string.h>

int main()
{	

	FILE *fptr;
	char filename[] = "flag.txt", c;
	int a,i,len,out=0;
	long long int in;
	printf("╔═╗┬─┐┌┐┌┐┌┐┬─┐   ╔═╗┬ ┌┐┌┐\n");
	printf("╠═╝├┬┘││├┘├ ├┬┘   ╠═╣│ │┬││\n");
	printf("╩  ┴└ └┘┴ └┘┴└    ╩ ╩┴┘└┘└┘\n");
	printf("Enter The License Key: ");
	scanf("%d",&in);
	out = start(in);
	if(out == 100796628)
	{
		printf("Congrats, Your flag is:\n");
		fptr = fopen(filename, "r");
        	if (fptr == NULL)
		{
        		printf("Cannot open flag.txt, are you entering the key on the server? \n"); 
			exit(0);
                  
		}
		c = fgetc(fptr); 
   		 while (c != EOF) 
    		{ 
        		printf ("%c", c); 
        		c = fgetc(fptr); 
    		} 
  
    		fclose(fptr); 
    		return 0; 
	}
	else
	{
		printf("WRONG!! you can't beat my Proper Algo ;)\n");
	}
	return 0;
}
int start(long long int a)
{	
	a = z(a);
	return a;
	
}
int z(long long int a)
{
	a = a + 28;
	a = a + 289999;
	a = o(a);
	return a;
}

int o(long long int a)
{
	int rem;
	rem = 99;
	a = a + rem;
	a = a - 29;
	a = a + 323232;
	a = m(a);
	return a;
}

int m(long long int a)
{
	
	a = a + 6929;
	a = a + 94554;
	a = b(a);
	return a;
}

int b(long long int a)
{
	
	a = a + 243334;
	a = a - 42242;
	a = i(a);
	return a;
}

int i(long long int a)
{
	
	a = a + 999;
	a = a - 55;
	a = e(a);
	return a;
}

int e(long long int a)
{
	
	a = a + 59;
	a = n(a);
	return a;
	
}

int n(long long int a)
{
	
	a = a + 66;
	a = a - 100;
	a = t(a);
	return a;
}

int t(long long int a)
{
	
	a = a - 27;
	a = a + 2999;
	return a;
}

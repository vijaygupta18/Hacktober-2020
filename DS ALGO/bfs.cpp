#include <bits/stdc++.h>
using namespace std;
void bfs(vector<int> &lvl, int root)
 { queue<int> q;
   vector<int> used(n+1);
   q.push(root);
   used[root]=1;
   
   while(!q.empty())
     { int v = q.front();
       q.pop();
	       for(auto u : adj[v]) 
	        { if(!used[u]) 
	            { q.push(u);
        		  used[u]=1;
		          lvl[u]=lvl[v]+1;
		        }
	        }
     }
 }

int main(){
int root;
int n;
cin >> n;
	vector <int> v(n);
	for(int i=0;i<n;i++) cin >> v[i];
	cin >> root;
	dfs(v,root );
		
	
}

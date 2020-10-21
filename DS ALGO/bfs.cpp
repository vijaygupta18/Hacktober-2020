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

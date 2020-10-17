#include <bits/stdc++.h>
using namespace std;

class Graph{

    int V;
    list<pair<int,int>> edgeList;
public:
    explicit Graph(int V){
        this->V = V;
    }

    void addEdge(int x,int y){
        edgeList.push_back(make_pair(x,y));
    }

    //DISJOINT SET

    int findSet(int x,vector<int> &parent){                 // Worst case complexity = O(n)
        if(parent[x]==-1)
            return x;
        else
            return findSet(parent[x],parent);
    }

    bool unionSet(int x,int y,vector<int> &parent){         // Complexity O(n)

        int s1 = findSet(x,parent);
        int s2 = findSet(y,parent);

        if(s1!=s2){
            parent[s1]=s2;
            return false;
        }
        return true;
    }

    bool detectCycle(){

        vector<int> parent(V);
        for(int i=0;i<V;i++)
            parent[i]=-1;

        for(auto edge: edgeList){
            bool status = unionSet(edge.first,edge.second,parent);
            if(status)
                return true;
            }
        return false;
    }

};

int main() {
    int vertex,edges;
    cin>>vertex>>edges;

    Graph g(vertex);
    for(int i=0;i<edges;i++){
        int x,y;
        cin>>x>>y;
        g.addEdge(x,y);
    }

    bool status = g.detectCycle();

    if(status)
        cout<<"Cycle is Present in Graph.";
    else
        cout<<"No Cycle exist.";
}

// input 1
//5 4
//0 1
//1 2
//2 3
//3 4

//input 2;
//6 6
//0 1
//0 2
//2 3
//1 3
//3 4
//4 5


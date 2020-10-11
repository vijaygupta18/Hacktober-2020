//doing this for free t-shirt
#include<bits/stdc++.h>
using namespace std;
typedef long long int ll;
struct node
{
	ll cnt,end;
	struct node *zero,*one;
};
string s;
struct node *addtotrie(struct node *root,ll n,ll pos)
{
	if(root==NULL)
	{
		root=new node;
		root->cnt=root->end=0;
		root->one=root->zero=NULL;
	}
	if(pos==n)
	{
		root->end=1;
		root->cnt++;
		return root;
	}
	pos++;
	if(s[pos]=='0')
	{
		root->zero=addtotrie(root->zero,n,pos);
	}
	else
	{
		root->one=addtotrie(root->one,n,pos);
	}
	return root;
}
ll findcnt(struct node *root,ll n,ll pos)
{
	if(root==NULL)
	{
		return 0;
	}
	if(pos==n)
	{
		return (root->end==1? root->cnt:0);
	}
	pos++;
	if(s[pos]=='0')
	{
		return findcnt(root->zero,n,pos);
	}
	else
	{
		return findcnt(root->one,n,pos);
	}
}
int main(void)
{
	// fastio
	ll n;
	cin>>n;
	struct node *root=new node;
	for(ll i=0;i<n;i++)
	{
		cin>>s;
		addtotrie(root,s.size(),0);
	}
	ll q;
	cin>>q;
	while(q--)
	{
		cin>>s;
		cout<<findcnt(root,s.size(),0)<<"\n";
	}
	return 0;
}

// this code is contributed by Lakshaypahuja21

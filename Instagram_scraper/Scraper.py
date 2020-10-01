import requests
import sys

def Instagram(username):
  r = requests.get("https://www.instagram.com/"+ username +"/?__a=1")
  if r.status_code == 200:
      res = r.json()['graphql']['user']
      print("\nUsername: " + res['username'])
      print("Full Name: "+res['full_name'])
      print("Biograph: " + res['biography'])
      print("URL: "+ str(res['external_url']))
      print("Followers: "+str(res['edge_followed_by']['count']))
      print("Following: "+str(res['edge_follow']['count']))
      print("has_channel: "+str(res['has_channel']))
      print("Business Account: "+str(res["is_business_account"]))
      if res['is_business_account']:
        print("Business Category: "+str(res['business_category_name']))
        print("category_enum: "+str(res['category_enum']))
      print("Joined Recently: "+str(res['is_joined_recently']))
      print("Private Account: "+str(res['is_private']))
      print("Verified: "+str(res['is_verified']))
      print("Facebook Account: "+str(res['connected_fb_page']))
      print("Profile Picture HD: " + res['profile_pic_url_hd'])
  elif r.status_code == 404:
      print("Error: Profile Not Found")
  else:
      print("Error: Something Went Wrong")

if len(sys.argv) == 1 or sys.argv[1] =="-h":
  print("Usage: python3 Scraper.py <username>")
else:
  Instagram(sys.argv[1])
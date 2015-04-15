import simplejson as js
import json
import urllib
import requests
headers = {'content-type': 'application/json'}
fp=open("loction.json").read()
d=js.loads(fp)
l=["schools","colleges","police"]
hashtable={}
for i in d.keys():
	for j in l:
		print d[i][0],d[i][1],d[i]
		url="https://api.foursquare.com/v2/venues/search?client_id=QAX31ZWNT4AP1JBVHVPYNBPPZ2G2SCJZZ4HN0JTYB2GI3GL4&client_secret=SG4GLUVPFY0YW42CADEVYEEVJNVRGM0TCWLXAGARMSI5HMFW&v=20130815&ll="+str(d[i][0])+","+str(d[i][1])+"&query="+j
		data=js.loads(urllib.urlopen(url).read())
		#hashtable[j]=data
		#print hashtable
		for k in data['response']['venues']:
			print k['name']
			print k['location']['lat']
			print k['location']['lng']
			print i
			print j
			payload={'name':k['name'],'lat':k['location']['lat'],'lon':k['location']['lng'],'place':i,'type':j}
			print payload
			url='http://localhost:7474/db/data/node'
			r=requests.post(url,data=json.dumps(payload),headers=headers)
			node=r.json()['self']
			print node
		#adding node to spatial rtree
			url='http://localhost:7474/db/data/index/node/restaurant'
			payload={"value":"dummy","key":"dummy","uri":node}
			r=requests.post(url,data=json.dumps(payload),headers=headers)
			print r
#fp=open("yo.json","w")
#json.dump(hashtable,fp)




"APFPP4414F" #PAN_NO
"4854980000227316 "  #CARD_NO
" BHOLU1" #PASWORD
"335" #CVV
"11/16" #EXPIRY_DATE
"SRI PRAKASH" #NAME_ON_CARD

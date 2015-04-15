import sys
import json
d={}
l=['enjoy', 'friends', 'dating', 'sleep', 'relax', 'family','miss','shopping']
l1=['movie_hall', 'coffee', 'ccd', 'mc donalds', 'kfc', 'wine', 'mall', 'local market', 'smoking', 'airport', 'medical', 'pvr', 'study_center', 'donuts', 'movers_packers', 'schools', 'colleges', 'police', 'pubs']

for i in l:
	d[i]={}
	
d['enjoy']={'explaination':"""Sir... I think you have choosen to enjoy a lot. I would suggest you with some movie spots and 
                        pubs where you can enjoy to get high and some quality free time at places where there would be 
                        less population. Enjoy sir.. and please don't et too high.. :P and if you are watching a movie please have a companion.... """,True:['movie_hall','coffee','ccd','mc donalds','kfc','wine','smoking','pvr','donuts','pubs']}
                        
d['friends']={'explaination':"""Sir... Friends are the choosen ones...Looking at your statement I think that you need a great 
                                companion and quality time with friends.. Let me plan out the best hangout places aound you that you can enjoy with your friends... Call someone.. At this time I cannot provide you with great humor as I myself being in a developpment phase.. :D :D """,True:['movie_hall','kfc','wine','mall','smoking','pvr']}
                     
                     
d['dating']={'explaination':""" Sir.. on ur demand I would try to arrange 
    the best place for you to dine and for the counter lucky one... I would ensure that
    the privacy is maintained.. Wish u good luck for ur date... ;)... Hope I would have been 
    alive to see that""",True:['movie_hall','coffee','ccd','pvr']}
    
    
    
d['family']={'explaination':"""sir... This is a family trip I suppose.. Whatever must be the situation you need to look sober in front of your family. I would plan out every possible places where there is no 
availability of wines,drugs and bakes.. :D :D  """,True:['mc donalds','kfc','mall','donuts']}
d['shopping']={'explaination':"""
   sir... you need to have a great experience time with what you shop... Give this
   me as a responsibility to optimize the places and filter the search so that you get the 
   most wise and good experience """,True:['mall','local market']}           
d['relax']={'explaination':"""Haha... Quite bored from life.. 
According to your statement you made... it seems you ought to plan a movie or a get together... 
Sir... I should plan out a combo strategy for u .... where you would get around with friends 
and chill out quite the whole day...
I would rather make a whole day schedule for u.. 
I would rather do a proimity search for food+movies... and hope I well serve the master.. :P D""",True:['movie_hall','pvr','smoking','wine','pubs'] }
d['sleep']={'explaination':"""
You need to take rest.. Please stop if u are driving... and I would prefer to get to nearby coffee shops and let me arrange that""",True:['cofee','ccd']}
d['school']={'explaination':"school stuff related",True:["schools","colleges","study_center"]}
d['miss']={'explaination':"""Sir.. According to you query I see you have started missing someone... Let me match interest 
of the person to your interest and plan out a gettogether and hangout.. :D :D"""}
print "Hello World"
#print d[sys.argv[1]]['explaination']
fp=open('inntell.json','wb')
json.dump(d,fp)


from py2neo import cypher,neo4j
import simplejson as js
g=neo4j.GraphDatabaseService()
print "Enter your name"
name=raw_input()
query="MATCH (n:People) where n.name='"+name+"' return n.interest"
l=cypher.execute(g,query)
l=l[0][0][0]
l=map(str,l)
print "This scripts maps your interests to the locations of yours in many cities in India"
d=js.loads(open("location.json").read())


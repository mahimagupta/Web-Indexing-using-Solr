import networkx as nx

print "Taking edgeList.txt as input..."
G = nx.read_edgelist("edgeList.txt", create_using=nx.DiGraph())

print "Calculating PageRank..."
pr = nx.pagerank(G, alpha=0.85, personalization=None, max_iter=30,
                 tol=1e-06, nstart=None, weight='weight', dangling=None)

print "Creating output file..."
outputFile = open("external_pageRankFile.txt", "w")

print "Writing to output..."

baseDir = "/Users/mahima/Desktop/IRAssignment4/latimes"

for key, value in pr.iteritems():  
    line = baseDir + str(key) + "=" + str(value)
    # print line
    outputFile.write(line + "\n")

print "Finished!"
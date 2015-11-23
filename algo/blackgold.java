class Edge(object):
    def __init__(self, u, v, w):
        self.source = u
        self.sink = v  
        self.capacity = w
    def __repr__(self):
        return "%s->%s:%s" % (self.source, self.sink, self.capacity)

class FlowNetwork(object):
    def __init__(self):
        self.adj = {}
        self.flow = {}
 
    def add_vertex(self, vertex):
        self.adj[vertex] = []
 
    def get_edges(self, v):
        return self.adj[v]
 
    def add_edge(self, u, v, w=0):
        
        edge = Edge(u,v,w)
        redge = Edge(v,u,0)
        edge.redge = redge
        redge.redge = edge
        self.adj[u].append(edge)
        self.adj[v].append(redge)
        self.flow[edge] = 0
        self.flow[redge] = 0
 
    def find_path(self, source, sink, path):
        if source == sink:
            return path
        for edge in self.get_edges(source):
            residual = edge.capacity - self.flow[edge]
            if residual > 0 and edge not in path:
                result = self.find_path( edge.sink, sink, path + [edge]) 
                if result != None:
                    return result
 
    def max_flow(self, source, sink):
        path = self.find_path(source, sink, [])
        while path != None:
            residuals = [edge.capacity - self.flow[edge] for edge in path]
            flow = min(residuals)
            for edge in path:
                self.flow[edge] += flow
                self.flow[edge.redge] -= flow
            path = self.find_path(source, sink, [])
        return sum(self.flow[edge] for edge in self.get_edges(source))

g = FlowNetwork()
cases = int(input())
start, end = map(int, input().split(' '))
for i in range(end+1):
    g.add_vertex(i)
tracker = dict()
for i in range(cases-1):
    temp = input().split(' ')
    form = temp[0]+' '+temp[1]
    if(form not in tracker):
        tracker[form] = int(temp[2])
        g.add_edge(int(temp[0]), int(temp[1]), int(temp[2]))

    elif(form in tracker):
        if(tracker[form] < int(temp[2])):
            tracker[form] = int(temp[2])
            g.add_edge(int(temp[0]), int(temp[1]), int(temp[2]))
    
                            

                            
   # g.add_edge(int(temp[0]), int(temp[1]), int(temp[2]))

if(start == end):
    print(0)
else:
    print (g.max_flow(start, end))

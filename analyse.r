library(ape)
compute=function(cvsName)
{
   csvFile=read.csv(csvName)
   d=as.matrix(dist(cbind(csvFile$n.lat,csvFile$n.lon)))
   d=(1/d)
   for(i in 1:length(d))
   {
     if(d[i]==Inf)
     {
       d[i]=0
     }
   }
   return(Moran.I(csvFile$r.score,d))  #if any value returns error then we do not have dispersed data and solution is simply custered
   
   
    
}

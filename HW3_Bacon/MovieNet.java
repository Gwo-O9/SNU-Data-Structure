import java.lang.*;
import java.util.*;
import java.io.*;

public class MovieNet {
  static final String KevinBacon = "Bacon, Kevin";
  ArrayList<ArrayList<Integer>> MovieList = new ArrayList<ArrayList<Integer>>();
  ArrayList<ArrayList<Integer>> ActorList = new ArrayList<ArrayList<Integer>>();
  int Mcount, Acount = 0;
  ArrayList<String> getMovieList = new ArrayList<String>();
  ArrayList<String> getActorList = new ArrayList<String>();
  HashMap<String,Integer> MovieMap = new HashMap<String,Integer>();
  HashMap<String,Integer> ActorMap = new HashMap<String,Integer>();
  ArrayList<TreeSet<Integer>> TreesetList = new ArrayList<TreeSet<Integer>>(); 
  ArrayList<ArrayList<Integer>> Treeset = new ArrayList<ArrayList<Integer>>();

  public MovieNet(LinkedList<String[]> movielines) {
    for(int i = 0; i<movielines.size();i++){
      MovieMap.put(movielines.get(i)[0], i);
      MovieList.add(new ArrayList<Integer>());
      getMovieList.add(movielines.get(i)[0]); 
      Mcount++;
      for(int j = 1;j<movielines.get(i).length;j++){
        if(!(ActorMap.containsKey(movielines.get(i)[j]))){
          ActorMap.put(movielines.get(i)[j], ActorMap.size());
          getActorList.add(movielines.get(i)[j]);
          ActorList.add(new ArrayList<Integer>());
          Acount++;
        }
        MovieList.get(i).add(ActorMap.get(movielines.get(i)[j]));
        ActorList.get(ActorMap.get(movielines.get(i)[j])).add(MovieMap.get(movielines.get(i)[0]));
      }
    }
        for(int k = 0; k<ActorList.size();k++){
          TreesetList.add(new TreeSet<Integer>());
          Treeset.add(new ArrayList<Integer>());
          for(int l = 0; l <ActorList.get(k).size();l++){
            TreesetList.get(k).addAll(MovieList.get(ActorList.get(k).get(l)));
          }
          Treeset.get(k).addAll(TreesetList.get(k));
        }
  }	// Constructor

  // [Q1]
  public String[] moviesby(String[] actors) {
    int n = 0;
    int tempcnt = 0;
    String[] a = new String[Mcount];
    String[] moviesbyout = null;
    for(int i = 0; i< actors.length;i++){
      if(!ActorMap.containsKey(actors[i])) return null;
    }
    for(int j =0; j<MovieList.size();j++){
      tempcnt = 0;
      for(int k = 0; k<actors.length;k++){
        if(!MovieList.get(j).contains(ActorMap.get(actors[k]))) break;
        tempcnt++;
      }
      if(tempcnt == actors.length){
        a[n] = getMovieList.get(j);
        n++;
      }
    }
    if(n > 0){
      moviesbyout = new String[n];
      System.arraycopy(a,0,moviesbyout,0,n);
    }
    return moviesbyout;
  }

  // [Q2]
  public String[] castin(String[] titles) {
    ArrayList<Integer> List = new ArrayList<Integer>();
    for(int i = 0; i<titles.length;i++){
      if(!MovieMap.containsKey(titles[i])) return null;
    }
    int a = MovieMap.get(titles[0]);
    List.addAll(MovieList.get(a));
    for(int j = 0; j<List.size();j++){
      for(int k = 1; k<titles.length;k++){
        a = MovieMap.get(titles[k]);
        if(!(MovieList.get(a).contains(List.get(j)))){
          List.remove(j);
          j--;
          break;
        }
      }
    }
    String[] castinout = null;
    if(List.size()>0){
      castinout = new String[List.size()];
      for(int l = 0; l<List.size();l++){
        castinout[l] = getActorList.get(List.get(l));
      }
    }
    return castinout;
  }

  // [Q3]
  public String[] pairmost(String[] actors) {
    int count= 0;
    int MAX = 0;
    String[] pairmostout = null;
    if(actors.length == 1) return pairmostout;
    for(int i = 0; i<actors.length;i++){
      int a = ActorMap.get(actors[i]);
      for(int j = i+1;j<actors.length;j++){
        int b = ActorMap.get(actors[j]);
        for(int k = 0;k<ActorList.get(b).size();k++){
          if(ActorList.get(a).contains(ActorList.get(b).get(k))) count++;
        }
        if(count > MAX){
          MAX = count;
          if(pairmostout == null) pairmostout = new String[2];
          pairmostout[0] = actors[i];
          pairmostout[1] = actors[j];
        }
        count = 0;
      }
    }
    return pairmostout;
  }

  // [Q4]
  public int Bacon(String actor) {
    return distance(KevinBacon,actor);
  }

  // [Q5]
  public int distance(String src, String dst) {
    if(!(ActorMap.containsKey(src) || !(ActorMap.containsKey(dst)))) return -1;
    ArrayList<Graph> a = new ArrayList<Graph>();
    Map<Integer,Boolean> isvisit = new HashMap<Integer,Boolean>();
    int count = 0;
    int act1, act2;
      for(int i = 0; i<Acount;i++){
        isvisit.put(i, false);
      }
      act1 = ActorMap.get(src);
      act2 = ActorMap.get(dst);
      if(act1 == act2) return 0;
      a.add(new Graph(null,act1,0,-1));
      isvisit.replace(act1, true);
      while(count<a.size()){
        Graph g = a.get(count);
        for(int j = 0; j<Treeset.get(g.h).size();j++){
          act2 = Treeset.get(g.h).get(j);
          if(!isvisit.get(act2)){
            if(act2 == ActorMap.get(dst)) return g.dist+1;
            a.add(new Graph(getActorList.get(g.h),act2,g.dist+1,count));
            isvisit.replace(act2,true);
          }
        }
        count++;
      }
      return -1;
  }

  // [Q6]
  public int npath(String src, String dst) {
    if(!(ActorMap.containsKey(src)) || !(ActorMap.containsKey(dst))) return 0;
    int count = 0;
    int act1, act2;
    int[] dist = new int[Acount];
    int[] vert = new int[Acount];
    ArrayList<Graph> a = new ArrayList<Graph>();
    Map<Integer,Boolean> isvisit = new HashMap<Integer,Boolean>();

    for(int i = 0; i<Acount;i++){
      isvisit.put(i, false);
      dist[i] = 0;
      vert[i] = 0;
    }
    act1 = ActorMap.get(src);
    act2 = ActorMap.get(dst);
    if(act1 == act2) return 1;
    a.add(new Graph(null,act1,0,-1));
    isvisit.replace(act1, true);
    vert[act1] = 1;
    while(count<a.size()){
      Graph g = a.get(count);
      if(g.h == ActorMap.get(dst)) break;
      for(int j = 0; j<Treeset.get(g.h).size();j++){
        act2 = Treeset.get(g.h).get(j);
        if(!isvisit.get(act2)){
          dist[act2] = dist[g.h]+1;
          vert[act2] = vert[g.h];
          a.add(new Graph(getActorList.get(g.h),act2,g.dist+1,count));
          isvisit.replace(act2,true);
        }
        else if(isvisit.get(act2) && dist[act2] > dist[g.h]){
          vert[act2] += vert[g.h];
        }
      }
      count++;
    }
    act2= ActorMap.get(dst);
    return vert[act2];
  }

  // [Q7]
  public String[] apath(String src, String dst) {
    if(!ActorMap.containsKey(src) || !ActorMap.containsKey(dst)) return null;
    int count = 0;
    int act1, act2;
    ArrayList<Graph> a = new ArrayList<Graph>();
    Map<Integer,Boolean> isvisit = new HashMap<Integer,Boolean>();
    ArrayList<String> path = new ArrayList<String>();
    String[] apathout = null;
    for(int i = 0; i<Acount;i++){
      isvisit.put(i,false);
    }
    act1 = ActorMap.get(src);
    act2 = ActorMap.get(dst);
    if(act1 == act2){
      apathout = new String[1];
      apathout[0] = src;
      return apathout;
    }
    int j;
    a.add(new Graph(null,act1,0,-1));
    isvisit.replace(act1, true);
    while(count<a.size()){
      Graph g = a.get(count);
      for(j = 0; j<Treeset.get(g.h).size();j++){
        act2 = Treeset.get(g.h).get(j);
        if(!isvisit.get(act2)){
          a.add(new Graph(getActorList.get(g.h),act2,g.dist+1,count));
          isvisit.replace(act2,true);
          if(act2 == ActorMap.get(dst)) break;
        }
      }
      if(j<Treeset.get(g.h).size()){
        path.add(getActorList.get(a.get(a.size()-1).h));
        act1 = a.get(a.size()-1).p;
        while(true){
          if(act1==-1) break;
          path.add(getActorList.get(a.get(act1).h));
          act1 = a.get(act1).p;
        }
        count = 0;
        apathout = new String[path.size()];
        for(int k = path.size()-1;k>=0;k--){
          apathout[count++] = path.get(k);
        }
        return apathout;
      }
      count++;  
    }
    return null;
  }

  // [Q8]
  public int eccentricity(String actor) {
    int count = 0;
    int act1, act2;
    int eccentricityout = 0;
    ArrayList<Graph> a = new ArrayList<Graph>();
    Map<Integer,Boolean> isvisit = new HashMap<Integer,Boolean>();
    if(!ActorMap.containsKey(actor)) return 0;
    for(int i =0; i<Acount;i++){
      isvisit.put(i,false);
    }
    act1 = ActorMap.get(actor);
    a.add(new Graph(null,act1,0,-1));
    isvisit.replace(act1,true);
    while(count<a.size()){
      Graph g = a.get(count);
      for(int j = 0;j<Treeset.get(g.h).size();j++){
        act2 = Treeset.get(g.h).get(j);
        if(!isvisit.get(act2)){
          if(g.dist+1>eccentricityout) eccentricityout = g.dist+1;
          a.add(new Graph(getActorList.get(g.h),act2,g.dist+1,count));
          isvisit.replace(act2,true);
        }
      }
      count++;
    }
    return eccentricityout;
  }

  // [Q9]
  public float closeness(String actor) {
    int count = 0;
    float closenessout = 0;
    int act1, act2;
    ArrayList<Graph> a = new ArrayList<Graph>();
    Map<Integer,Boolean> isvisit = new HashMap<Integer,Boolean>();
    int[] dis = new int[Acount];
    if(!ActorMap.containsKey(actor)) return 0;
    for(int i = 0; i<Acount;i++){
      isvisit.put(i,false);
      dis[i] = -1;
    }
    act1 = ActorMap.get(actor);
    a.add(new Graph(null,act1,0,-1));
    isvisit.replace(act1,true);
    dis[act1] = 0;
    while(count<a.size()){
      Graph g = a.get(count);
      for(int j = 0; j<Treeset.get(g.h).size();j++){
        act2 = Treeset.get(g.h).get(j);
        if(!isvisit.get(act2)){
          dis[act2] = g.dist+1;
          a.add(new Graph(getActorList.get(g.h),act2,g.dist+1,count));
          isvisit.replace(act2,true);
        }
      }
      count++;
    }
    for(int k = 0; k<Acount;k++){
      if(dis[k]>0) closenessout += 1/Math.pow(2,dis[k]);
    }
    return closenessout;
  }

}


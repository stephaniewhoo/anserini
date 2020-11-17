package io.anserini.ltr.feature;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.lucene.index.Term;

import java.io.IOException;
import java.util.*;

public class QueryContext {
    public List<String> queryText;
    public List<String> queryTokens;
    public Map<String,Integer> queryFreqs;
    public int querySize;

    //todo pre-retrieval feature here
    public Map<String, Float> cache;

    public QueryContext(List<String> queryText, List<String> queryTokens){
        this.queryTokens = queryTokens;
        this.queryText = queryText;
        this.querySize = queryTokens.size();
        this.queryFreqs = new HashMap<>();
        for (String token : queryTokens)
            queryFreqs.put(token, queryFreqs.getOrDefault(token,0)+1);
        this.cache = new HashMap<>();
    }

    public Integer getQueryFreq(String queryToken) {
        return queryFreqs.getOrDefault(queryToken,0);
    }

    public List<Pair<String, String>> genQueryPair() {
        List<Pair<String, String>> queryPairs = new ArrayList<>();
        for (int i = 0; i < queryTokens.size() - 1; i++) {
            for (int j = i +1; j < queryTokens.size(); j++) {
                queryPairs.add(Pair.of(queryTokens.get(i),queryTokens.get(j)));
            }
        }
        return queryPairs;
    }

    public List<Pair<String, String>> genQueryBigram() {
        List<Pair<String, String>> queryBigram = new ArrayList<>();
        for (int i = 0; i < queryTokens.size() - 1; i++) {
            queryBigram.add(Pair.of(queryTokens.get(i),queryTokens.get(i+1)));
        }
        return queryBigram;
    }



}

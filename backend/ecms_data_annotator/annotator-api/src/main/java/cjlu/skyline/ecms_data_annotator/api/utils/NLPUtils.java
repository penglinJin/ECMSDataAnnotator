package cjlu.skyline.ecms_data_annotator.api.utils;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

import java.util.Properties;

public class NLPUtils {

    public static int getScore(String s){
        int score=-1;
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
        // gender,lemma,ner,parse,pos,sentiment,sspplit, tokenize
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // read some text in the text variable

        String sentimentText = s;
        //String[] ratings = {"Very Negative", "Negative", "Neutral", "Positive", "Very Positive"};
        //String[] ratings = {"Negative", "Positive"};
        Annotation annotation = pipeline.process(sentimentText);
        for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
            Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
            score = RNNCoreAnnotations.getPredictedClass(tree);

            //System.out.println("sentence:'" + sentence + "' has a score of" + (score - 2) + " rating:" + ratings[score]);
            //System.out.println(tree);
        }
        return score;
    }

}

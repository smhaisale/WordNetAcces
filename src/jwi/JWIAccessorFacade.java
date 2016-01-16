package jwi;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.StringUtils.*;
/**
 * Created by shahbaaz on 1/16/16.
 */
public class JWIAccessorFacade {

    private IDictionary dict;

    private String path = Constants.WORD_NET_PATH;

    public JWIAccessorFacade() {
        this.initialize();
    }

    private void initialize() {

        try {
            URL url = new URL("file", null, path + "/dict");
            dict = new Dictionary(url);
            dict.open();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void test(String testWord) {

        initialize();

        IIndexWord idxWord = dict.getIndexWord(testWord, POS.NOUN);
        IWordID wordID = idxWord.getWordIDs().get(0);
        IWord word = dict.getWord(wordID);
        System.out.println("Id = " + wordID);
        System.out.println("Lemma = " + word.getLemma());
        System.out.println("Gloss = " + word.getAdjectiveMarker());
        System.out.println("Lexical ID = " + word.getLexicalID());
        System.out.println("Related Map = " + word.getRelatedMap());
        System.out.println("Related Words = " + word.getRelatedWords());
        System.out.println("Sense Key = " + word.getSenseKey());
        System.out.println("Verb Frames = " + word.getVerbFrames());
        System.out.println("Synset Words = " + word.getSynset().getWords());
        System.out.println("Related Synsets = " + word.getSynset().getRelatedSynsets());
        System.out.println("Synset Type = " + word.getSynset().getType());
    }

    public List<ISynsetID> getRelatedSynSets(String word) {
        IIndexWord idxWord = dict.getIndexWord(word, POS.NOUN);
        IWordID wordID = idxWord.getWordIDs().get(0);
        IWord iWord = dict.getWord(wordID);
        List<ISynsetID> synSetIds = iWord.getSynset().getRelatedSynsets();
        return synSetIds;
    }

    public List<String> getClosestWords(String word, int count)
    {
        List<String> synSet = new ArrayList<String>();
        List<ISynsetID> synSetIds = this.getRelatedSynSets(word);
        for(ISynsetID id: synSetIds) {
            ISynset s = dict.getSynset(id);


            for(IWord w: s.getWords()) {
                if (!((w.getLemma().contains(word)) && word.contains(w.getLemma()))) {
                    System.out.println(w.getLemma());
                    synSet.add(w.getLemma());
                    w.getPOS();
                    count--;
                }
            }
            if(count == 0)
            {
                break;
            }
        }
        return synSet;
    }

}



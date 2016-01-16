package jwi;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

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

    public void test() {

        initialize();

        // look up first sense of the word "dog"
        IIndexWord idxWord = dict.getIndexWord("dog", POS.NOUN);
        IWordID wordID = idxWord.getWordIDs().get(0);
        IWord word = dict.getWord(wordID);
        System.out.println("Id = " + wordID);
        System.out.println("Lemma = " + word.getLemma());
        System.out.println("Gloss = " + word.getSynset().getGloss());

    }
}

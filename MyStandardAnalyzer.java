/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/* Modified 9 January 2020 by Daniel Lizotte dlizotte@uwo.ca */
package lucenepackage;
import java.io.IOException;

import java.io.Reader;

import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.search.similarities.ClassicSimilarity;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.StopwordAnalyzerBase;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.WordlistLoader;



import java.util.Arrays;
import java.util.List;



import java.lang.Object;
import org.apache.lucene.util.AttributeSource;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.TokenFilter;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.WordlistLoader;
import org.apache.lucene.analysis.en.PorterStemFilter;


import java.lang.Object;

import org.apache.lucene.search.similarities.ClassicSimilarity;
import org.apache.lucene.search.similarities.Similarity;
import org.apache.lucene.search.similarities.TFIDFSimilarity;
import org.apache.lucene.search.similarities.ClassicSimilarity;

import java.util.Collection;
import java.util.List;

/**
 * Filters {@link StandardTokenizer} with {@link LowerCaseFilter} and
 * {@link StopFilter}, using a configurable list of stop words.
 *
 * @since 3.1
 */

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.lang.Object;
import org.apache.lucene.util.AttributeSource;

public final class MyStandardAnalyzer extends StopwordAnalyzerBase {
	


	//Stopping 
	// list that consists of (only) the following words: "about", "dost", "from", "hath", "his", "O", "that", "the", "thou"
	// Creating a CharArraySet and naming it as "wordCharArraySet" and this contains the list of our stopwords
	static final List<String> wordList = Arrays.asList("about","dost","from","hath","his","O","that","the","thou");
	static final CharArraySet wordCharArraySet = new CharArraySet(wordList,true);//To ignore the case sensitivity, we change False to True
																				//Since stopwords need to be lowercase otherwise they will not have any effect
																					
	
	
	
	
  /** Default maximum allowed token length */
  public static final int DEFAULT_MAX_TOKEN_LENGTH = 255;

  private int maxTokenLength = DEFAULT_MAX_TOKEN_LENGTH;

  /** Builds an analyzer with the given stop words.
   * @param stopWords stop words */
  public MyStandardAnalyzer(CharArraySet wordCharArraySet) {
    super(wordCharArraySet);
  }

  /** Builds an analyzer with no stop words.
   */
  public MyStandardAnalyzer() {
    this(CharArraySet.EMPTY_SET);
  }

  /** Builds an analyzer with the stop words from the given reader.
   * @see WordlistLoader#getWordSet(Reader)
   * @param stopwords Reader to read stop words from */
  public MyStandardAnalyzer(Reader stopwords) throws IOException {
    this(loadStopwordSet(stopwords));
  }

  /**
   * Set the max allowed token length.  Tokens larger than this will be chopped
   * up at this token length and emitted as multiple tokens.  If you need to
   * skip such large tokens, you could increase this max length, and then
   * use {@code LengthFilter} to remove long tokens.  The default is
   * {@link MyStandardAnalyzer#DEFAULT_MAX_TOKEN_LENGTH}.
   */
  public void setMaxTokenLength(int length) {
    maxTokenLength = length;
  }
    
  /** Returns the current maximum token length
   * 
   *  @see #setMaxTokenLength */
  public int getMaxTokenLength() {
    return maxTokenLength;
  }

  @Override
  protected TokenStreamComponents createComponents(final String fieldName) {
    final StandardTokenizer src = new StandardTokenizer();
    src.setMaxTokenLength(maxTokenLength);
    TokenStream tok = new LowerCaseFilter(src);
    tok = new StopFilter(tok,wordCharArraySet);//Here we are passing our stopwords list "wordCharArraySet" with tokens to remove stopwords
    return new TokenStreamComponents(r -> {
      src.setMaxTokenLength(MyStandardAnalyzer.this.maxTokenLength);
      src.setReader(r);
    },new PorterStemFilter(tok));//Stemming 
    							// Performing stemming using Porter stemmer.
    							//The PorterStemFilter class implements Porter stemming 
    							//And then passing tokens as tok in it
    
  }
  
}


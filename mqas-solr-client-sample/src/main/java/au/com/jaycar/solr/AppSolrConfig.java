package au.com.jaycar.solr;

import java.util.Arrays;
import java.util.Optional;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.CloudSolrClient.Builder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.SolrPageRequest;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

@Configuration
@EnableSolrRepositories
public class AppSolrConfig {

	@Bean
	public SolrClient solrClient() {
		Builder builder = new Builder(Arrays.asList("node01:2181", "node02:2181", "node03:2181"), Optional.empty());
		return builder.build();
	}

	@Bean
	public SolrTemplate solrTemplate(SolrClient solrClient) {
		return new SolrTemplate(solrClient);
	}

	@Bean
	public Pageable pageable() {
		return new SolrPageRequest(0, 10);
	}

}

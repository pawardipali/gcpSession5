import org.apache.beam.runners.dataflow.DataflowRunner;
import org.apache.beam.runners.dataflow.options.DataflowPipelineOptions;
import org.apache.beam.sdk.Pipeline;

import org.apache.beam.sdk.io.TextIO;

import org.apache.beam.sdk.options.PipelineOptions;

import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.io.gcp.pubsub.PubsubIO;

import org.apache.beam.sdk.transforms.Create;

import java.util.Arrays;

import java.util.List;

public class Example {

    public static void main(String[] args) {

        DataflowPipelineOptions pipelineOptions = PipelineOptionsFactory.as(DataflowPipelineOptions.class);

        pipelineOptions.setProject("lyrical-amulet-308012");

        pipelineOptions.setJobName("LabitSession2");

        pipelineOptions.setRegion("australia-southeast1");

        pipelineOptions.setRunner(DataflowRunner.class);

        pipelineOptions.setGcpTempLocation("gs://smalltech//tmp");

        Pipeline pipeline = Pipeline.create(pipelineOptions);

        final List<String> input = Arrays.asList("New_words", "new_list", "detaflow", "runner", "cloud bucket");

        pipeline.apply(Create.of(input)).apply(TextIO.write().to("gs://snalltech//results//demo2").withSuffix(".txt"));
        pipeline.apply(Create.of(input)).apply(PubsubIO.writeStrings().to("projects/lyrical-amulet-308012/topics/LabITDemo"));

        pipeline.run().waitUntilFinish();
    }
}
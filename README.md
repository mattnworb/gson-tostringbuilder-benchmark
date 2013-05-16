A benchmark of using various ways to generate toString representations of POJOs using [Caliper](https://code.google.com/p/caliper/):

1. GSON.toJson()
2. Implementing toString() with commons-lang3 ToStringBuilder.
3. Implementing toString() with an IDE macro using StringBuilder directly.

Sample results can be seen at https://microbenchmarks.appspot.com/runs/8a853379-f1a7-484c-892b-de3d4cd7b11a, which was
run with the following arguments passed to Caliper:

    --print-config --run-name "Gson vs ToStringBuilder default warmup/timelimit" --verbose
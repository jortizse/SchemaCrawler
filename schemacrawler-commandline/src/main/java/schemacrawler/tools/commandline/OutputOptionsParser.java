/*
========================================================================
SchemaCrawler
http://www.schemacrawler.com
Copyright (c) 2000-2019, Sualeh Fatehi <sualeh@hotmail.com>.
All rights reserved.
------------------------------------------------------------------------

SchemaCrawler is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

SchemaCrawler and the accompanying materials are made available under
the terms of the Eclipse Public License v1.0, GNU General Public License
v3 or GNU Lesser General Public License v3.

You may elect to redistribute this code under any of these licenses.

The Eclipse Public License is available at:
http://www.eclipse.org/legal/epl-v10.html

The GNU General Public License v3 and the GNU Lesser General Public
License v3 are available at:
http://www.gnu.org/licenses/

========================================================================
*/

package schemacrawler.tools.commandline;


import static sf.util.Utility.isBlank;

import java.nio.file.Path;
import java.nio.file.Paths;

import schemacrawler.schemacrawler.Config;
import schemacrawler.tools.options.OutputOptions;
import schemacrawler.tools.options.OutputOptionsBuilder;

/**
 * Parses the command-line.
 *
 * @author Sualeh Fatehi
 */
public final class OutputOptionsParser
  extends BaseOptionsParser<OutputOptions>
{

  private static final String OUTPUT_FILE = "outputfile";
  private static final String OUTPUT_FORMAT = "outputformat";

  final OutputOptionsBuilder outputOptionsBuilder;

  public OutputOptionsParser(final Config config)
  {
    super(config);
    normalizeOptionName(OUTPUT_FORMAT, "fmt");
    normalizeOptionName(OUTPUT_FILE, "o");

    outputOptionsBuilder = OutputOptionsBuilder.builder().fromConfig(config);
  }

  @Override
  public OutputOptions getOptions()
  {
    final String outputFileName = config.getStringValue(OUTPUT_FILE, null);
    consumeOption(OUTPUT_FILE);
    if (!isBlank(outputFileName))
    {
      final Path outputFile = Paths.get(outputFileName).toAbsolutePath();
      outputOptionsBuilder.withOutputFile(outputFile);
    }

    final String outputFormatValue = config.getStringValue(OUTPUT_FORMAT, null);
    consumeOption(OUTPUT_FORMAT);
    if (!isBlank(outputFormatValue))
    {
      outputOptionsBuilder.withOutputFormatValue(outputFormatValue);
    }

    return outputOptionsBuilder.toOptions();
  }

}

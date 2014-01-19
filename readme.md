# NoIemFramesWG

A simple and lightweight plugin that blocks the placement of item frames in configurable WorldGuard regions.

## Download
**Download at:** http://ci.chaseoes.com/job/NoItemFramesWG

## Commands
<table>
  <tr>
    <th>Command Usage</th><th>Description</th>
  </tr>
  <tr>
    <td>/noitemframeswg</td>
    <td>General plugin information.</td>
  </tr>
  <tr>
    <td>/noitemframeswg add <region name></td>
    <td>Add a region to the configuration.</td>
  </tr>
  <tr>
    <td>/noitemframeswg remove <region name></td>
    <td>Remove a region from the configuration.</td>
  </tr>
</table>

## Permissions
<table>
  <tr>
    <th>Permission</th><th>Description</th>
  </tr>
  <tr>
    <td>noitemframeswg.admin</td>
    <td>Permission to use all commands.</td>
  </tr>
  <tr>
    <td>noitemframeswg.bypass</td>
    <td>Permission to bypass the plugin and place item frames in all regions.</td>
  </tr>
</table>

## Configuration
    placement-denied-message: '&cItem frames are disabled in the market to prevent lag. Sorry!'
    regions:
      - example
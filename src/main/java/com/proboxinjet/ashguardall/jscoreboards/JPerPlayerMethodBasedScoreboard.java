package com.proboxinjet.ashguardall.jscoreboards;

import org.bukkit.entity.Player;

import java.util.*;

public final class JPerPlayerMethodBasedScoreboard extends JPerPlayerScoreboard {
  private final Map<UUID, String> playerToTitleMap = new HashMap<>();
  private final Map<UUID, List<String>> playerToLinesMap = new HashMap<>();

  public JPerPlayerMethodBasedScoreboard(JScoreboardOptions options) {
    super(options);

    setGenerateTitleFunction(this::getTitle);
    setGenerateLinesFunction(this::getLines);
  }

  public JPerPlayerMethodBasedScoreboard() {
    this(JScoreboardOptions.defaultOptions);
  }

  private String getTitle(Player player) {
    return playerToTitleMap.getOrDefault(player.getUniqueId(), "");
  }

  public void setTitle(Player player, String title) {
    playerToTitleMap.put(player.getUniqueId(), title);
    updateScoreboard();
  }

  private List<String> getLines(Player player) {
    return playerToLinesMap.get(player.getUniqueId());
  }

  public void setLines(Player player, List<String> lines) {
    playerToLinesMap.put(player.getUniqueId(), lines);
    updateScoreboard();
  }

  public void setLines(Player player, String... lines) {
    playerToLinesMap.put(player.getUniqueId(), Arrays.asList(lines));
    updateScoreboard();
  }
}

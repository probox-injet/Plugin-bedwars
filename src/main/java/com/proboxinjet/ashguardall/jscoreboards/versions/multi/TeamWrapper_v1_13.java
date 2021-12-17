package com.proboxinjet.ashguardall.jscoreboards.versions.multi;

import com.proboxinjet.ashguardall.jscoreboards.abstraction.InternalTeamWrapper;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.Team;

public class TeamWrapper_v1_13 extends InternalTeamWrapper
{
  @Override
  public void setColor(Team team, ChatColor color) {
    team.setColor(color);
  }
}

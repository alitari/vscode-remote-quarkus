# .bashrc

# User specific aliases and functions

alias rm='rm -i'
alias cp='cp -i'
alias mv='mv -i'

# Source global definitions
if [ -f /etc/bashrc ]; then
        . /etc/bashrc
fi

shopt -s histappend
PROMPT_COMMAND="history -a;$PROMPT_COMMAND"
export HISTFILE=/workspaces/vscode-remote-quarkus/.bash_history

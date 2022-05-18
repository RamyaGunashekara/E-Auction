package com.fse3.cqrs.core.infrastructure;

import com.fse3.cqrs.core.commands.BaseCommand;
import com.fse3.cqrs.core.commands.CommandHandlerMethod;

public interface CommandDispatcher {
    <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler);
    void send(BaseCommand command);
}

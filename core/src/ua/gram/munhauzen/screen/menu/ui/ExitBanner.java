package ua.gram.munhauzen.screen.menu.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;

import ua.gram.munhauzen.ButtonBuilder;
import ua.gram.munhauzen.FontProvider;
import ua.gram.munhauzen.MunhauzenGame;
import ua.gram.munhauzen.entity.StoryAudio;
import ua.gram.munhauzen.screen.MenuScreen;
import ua.gram.munhauzen.screen.menu.fragment.ExitFragment;
import ua.gram.munhauzen.ui.Banner;
import ua.gram.munhauzen.ui.PrimaryButton;
import ua.gram.munhauzen.utils.Log;

public class ExitBanner extends Banner<MenuScreen> {

    final ExitFragment fragment;
    PrimaryButton yesBtn, noBtn;

    public ExitBanner(ExitFragment fragment) {
        super(fragment.screen);

        this.fragment = fragment;
    }

    @Override
    public Texture getBackgroundTexture() {
        return screen.assetManager.get("ui/banner_fond_3.png", Texture.class);
    }

    @Override
    public Table createContent() {

        float minWidth = MunhauzenGame.WORLD_WIDTH * .7f;

        Table content = new Table();
        content.pad(20, MunhauzenGame.WORLD_WIDTH * .1f, 40, MunhauzenGame.WORLD_WIDTH * .1f);

        float cellMinWidth = minWidth - content.getPadLeft() - content.getPadRight();

        Table rows = new Table();

        Label.LabelStyle style = new Label.LabelStyle(
                screen.game.fontProvider.getFont(FontProvider.h4),
                Color.BLACK
        );

        for (String sentence : screen.game.t("exit_banner.title").split("\n")) {
            Label label = new Label(sentence, style);
            label.setAlignment(Align.center);
            label.setWrap(true);

            rows.add(label)
                    .minWidth(cellMinWidth - 40)
                    .padBottom(10)
                    .row();
        }

        Table columns = new Table();

        columns.add(rows).padBottom(30)
                .minWidth(cellMinWidth)
                .center().row();

        yesBtn = game.buttonBuilder.danger(screen.game.t("exit_banner.yes_btn"), new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

                Log.i(tag, "yes clicked");

                try {
                    fragment.root.setTouchable(Touchable.disabled);

                    yesBtn.setDisabled(true);

                    screen.game.stopCurrentSfx();
                    StoryAudio audio = screen.game.currentSfx = game.sfxService.onExitYesClicked();

                    if (audio != null) {
                        Timer.instance().scheduleTask(new Timer.Task() {
                            @Override
                            public void run() {
                                onYesClicked();
                            }
                        }, audio.duration / 1000f);
                    } else {
                        onYesClicked();
                    }

                } catch (Throwable e) {
                    Log.e(tag, e);
                }
            }
        });

        noBtn = game.buttonBuilder.danger(screen.game.t("exit_banner.no_btn"), new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

                Log.i(tag, "no clicked");

                try {
                    fragment.root.setTouchable(Touchable.disabled);

                    noBtn.setDisabled(true);

                    screen.game.stopCurrentSfx();
                    StoryAudio audio = screen.game.currentSfx = game.sfxService.onExitNoClicked();

                    if (audio != null) {
                        Timer.instance().scheduleTask(new Timer.Task() {
                            @Override
                            public void run() {
                                onNoClicked();
                            }
                        }, audio.duration / 1000f);
                    } else {
                        onNoClicked();
                    }

                } catch (Throwable e) {
                    Log.e(tag, e);
                }
            }
        });

        Table buttons = new Table();
        buttons.add(yesBtn).left().expandX().padRight(5)
                .width(ButtonBuilder.BTN_PRIMARY_SM_WIDTH)
                .height(ButtonBuilder.BTN_PRIMARY_SM_HEIGHT);
        buttons.add(noBtn).right().expandX().padLeft(5)
                .width(ButtonBuilder.BTN_PRIMARY_SM_WIDTH)
                .height(ButtonBuilder.BTN_PRIMARY_SM_HEIGHT);

        content.add(columns).row();
        content.add(buttons).row();

        return content;
    }

    private void onNoClicked() {
        fragment.fadeOut(new Runnable() {
            @Override
            public void run() {
                screen.destroyBanners();
            }
        });
    }

    private void onYesClicked() {
        game.navigator.closeApp();
    }

}
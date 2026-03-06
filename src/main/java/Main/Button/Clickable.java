package Main.Button;

/**
 * Interface defining basic interactive behavior for UI components.
 *
 * Classes implementing this interface must provide logic for
 * handling click events and mouse hover interactions.
 */
public interface Clickable {

    /**
     * Handles the click event on the component.
     */
    void handleClick();

    /**
     * Triggered when the mouse cursor enters the component area.
     */
    void onHoverEnter();

    /**
     * Triggered when the mouse cursor leaves the component area.
     */
    void onHoverExit();

}
